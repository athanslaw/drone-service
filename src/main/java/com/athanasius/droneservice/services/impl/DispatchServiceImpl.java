package com.athanasius.droneservice.services.impl;

import com.athanasius.droneservice.dto.DispatchDto;
import com.athanasius.droneservice.enums.DroneState;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.model.Dispatch;
import com.athanasius.droneservice.model.Drones;
import com.athanasius.droneservice.model.Medication;
import com.athanasius.droneservice.repository.DispatchRepository;
import com.athanasius.droneservice.response.DispatchResponse;
import com.athanasius.droneservice.response.DronesResponse;
import com.athanasius.droneservice.services.DispatchService;
import com.athanasius.droneservice.services.DronesService;
import com.athanasius.droneservice.services.MedicationService;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispatchServiceImpl implements DispatchService {

  @Autowired
  DronesService dronesService;
  @Autowired
  MedicationService medicationService;

  @Autowired
  DispatchRepository dispatchRepository;

  private String generateId(){
    return new Date().getTime()+generateFourDigitRandomID();
  }

  private String generateFourDigitRandomID(){
    SecureRandom rand = new SecureRandom();
    return  (rand.nextInt(8999) + 1000)+"";
  }

  @Override
  public DispatchResponse setUpDispatch(DispatchDto dispatchDto) throws BadRequestException {
    if(dispatchDto.getMedicationCode()==null || dispatchDto.getDroneSerialNo()==null)
      throw new BadRequestException("medicationCode and droneSerialNo should not be null");
    Dispatch dispatch;
    try {
      Drones drones = dronesService.getDronesBySerialNo(dispatchDto.getDroneSerialNo()).getDrone();
      if(drones.getBatteryCapacity() < 25){
        throw new BadRequestException("Drone battery is low");
      }
      Medication medication = medicationService.getMedicationsByCode(dispatchDto.getMedicationCode()).getMedication();
      if(!medication.getStatus()){
        throw new BadRequestException("Medication is currently disabled");
      }
      List<Dispatch> dispatchList = dispatchRepository.findByDrone(drones);

      int droneUsedSpace = getDroneUsedSpace(dispatchList);
      int droneAvailableSpace = drones.getWeightLimit().intValue() - droneUsedSpace;
      if(droneAvailableSpace < (droneUsedSpace + medication.getWeight())){
        throw new BadRequestException("Medication weight is higher than the drone's available space, kindly add a medication with weight below "+droneAvailableSpace);
      }
      LocalDateTime date = LocalDateTime.now();
      dispatchDto.setTimestamp(date);
      dispatchDto.setId(generateId());
      dispatchDto.setDrone(drones);
      dispatchDto.setMedication(medication);
      dispatch = dispatchDto.toModel();
      dispatchRepository.save(dispatch);
      if(droneAvailableSpace < 10) {
        dronesService.updateDroneState(dispatchDto.getDroneSerialNo(), DroneState.LOADED);
      }else {
        dronesService.updateDroneState(dispatchDto.getDroneSerialNo(), DroneState.LOADING);
      }
    }
    catch (NotFoundException e){
      throw new BadRequestException(e.getMessage());
    }
    return new DispatchResponse("00", "Dispatch setup successfully. Tracker ID is "+dispatchDto.getId(), dispatch);
  }

  @Override
  public DispatchResponse triggerDispatch(String droneSerialNumber) throws NotFoundException {
    List<Dispatch> dispatch = dispatchRepository.findByDrone(dronesService.getDronesBySerialNo(droneSerialNumber).getDrone());
    if(dispatch.isEmpty()) throw new NotFoundException("Drone is not yet loaded");
    dronesService.updateDroneState(droneSerialNumber, DroneState.DELIVERING);
    return new DispatchResponse("00", "Dispatch triggered successfully.", dispatch);
  }

  @Override
  public DispatchResponse getDroneContent(String droneSerialNumber) throws NotFoundException {
    List<Dispatch> dispatch = dispatchRepository.findByDrone(dronesService.getDronesBySerialNo(droneSerialNumber).getDrone());
    if(dispatch.isEmpty()) throw new NotFoundException("Drone is not yet loaded");
    return new DispatchResponse("00", "Records fetched", dispatch);
  }

  @Override
  public DispatchResponse getDronesByMedication(String medicationCode) throws NotFoundException {
    List<Dispatch> dispatch = dispatchRepository.findByMedication(medicationService.getMedicationsByCode(medicationCode).getMedication());
    if(dispatch.isEmpty()) throw new NotFoundException("Medication is not yet added to any drone");
    return new DispatchResponse("00", "Records fetched", dispatch);
  }

  @Override
  public DronesResponse getAvailableDrones() {
    List<Drones> drones = dronesService.retrieveAllDrones().getDrones();
    List<Drones> availableDrones = new ArrayList<>();
    drones.forEach(drone -> {
      List<Dispatch> dispatchList = dispatchRepository.findByDrone(drone);
      int droneUsedSpace = getDroneUsedSpace(dispatchList);
      int droneAvailableSpace = drone.getWeightLimit().intValue() - droneUsedSpace;
      if(droneAvailableSpace > 10 && "IDLE,LOADING".contains(drone.getState().name())){
        availableDrones.add(drone);
      }
    });
    return new DronesResponse("00", "Records fetched", availableDrones);
  }

  private int getDroneUsedSpace(List<Dispatch> dispatchList){
    return dispatchList.stream().map(Dispatch::getMedication).map(Medication::getWeight).mapToInt(Integer::intValue)
        .sum();
  }

}
