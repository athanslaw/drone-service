package com.athanasius.droneservice.services;

import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.response.DronesResponse;

public interface DronesService {
  DronesResponse registerDrone(DronesDto dronesDto) throws BadRequestException, DuplicateException;
  DronesResponse retrieveAllDrones();
  DronesResponse getDronesByModel(String model) throws BadRequestException, NotFoundException;
  DronesResponse getDronesByState(String state) throws BadRequestException, NotFoundException;
}
