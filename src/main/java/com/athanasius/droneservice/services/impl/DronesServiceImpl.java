package com.athanasius.droneservice.services.impl;

import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.model.Drones;
import com.athanasius.droneservice.repository.DronesRepository;
import com.athanasius.droneservice.response.DronesResponse;
import com.athanasius.droneservice.services.DronesService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

@Service
public class DronesServiceImpl implements DronesService {

  @Autowired
  DronesRepository dronesRepository;

  @Override
  public DronesResponse registerDrone(DronesDto dronesDto) throws BadRequestException {
    Optional<Drones> drone = dronesRepository.findById(dronesDto.getSerialNo());
    if(drone.isEmpty()) {
      dronesRepository.save(dronesDto.toModel());
    }else{
      throw new DuplicateException("Record already exists with the submitted serial number.");
    }
    return new DronesResponse("00", "Record save successfully.");
  }

  @Override
  public DronesResponse retrieveAllDrones() {
    List<Drones> drones = dronesRepository.findAll();
    return new DronesResponse("00", "Drones retrieved successfully.", drones);
  }
}
