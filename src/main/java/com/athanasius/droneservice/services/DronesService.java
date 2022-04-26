package com.athanasius.droneservice.services;

import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.response.DronesResponse;

public interface DronesService {
  DronesResponse registerDrone(DronesDto dronesDto) throws BadRequestException;
  DronesResponse retrieveAllDrones();
}
