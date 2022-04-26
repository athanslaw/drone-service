package com.athanasius.droneservice.services;

import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.response.DronesResponse;

public interface DronesService {
  DronesResponse registerDrone(DronesDto dronesDto);
  DronesResponse retrieveAllDrones();
}
