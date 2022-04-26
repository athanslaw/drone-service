package com.athanasius.droneservice.services.impl;

import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.model.Drones;
import com.athanasius.droneservice.repository.DronesRepository;
import com.athanasius.droneservice.response.DronesResponse;
import com.athanasius.droneservice.services.DronesService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

@Service
public class DronesServiceImpl implements DronesService {

  @Autowired
  DronesRepository dronesRepository;

  @Override
  public DronesResponse registerDrone(DronesDto dronesDto) {
    try{
      dronesRepository.save(new Drones(dronesDto.getSerialNo(), dronesDto.getModel(), dronesDto.getWeightLimit(), dronesDto.getBatteryCapacity(), dronesDto.getState()));
    }catch (DuplicateException e){
      return new DronesResponse("00", "Record already exists with the submitted serial number.");
    }catch (BadRequest e){
      return new DronesResponse("00", "Bad Request. "+ e.getMessage());
    }
    return new DronesResponse("00", "Record save successfully.");
  }

  @Override
  public DronesResponse retrieveAllDrones() {
    List<Drones> drones = dronesRepository.findAll();
    return new DronesResponse("00", "Drones retrieved successfully.", drones);
  }
}
