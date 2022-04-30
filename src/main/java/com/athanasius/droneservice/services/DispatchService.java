package com.athanasius.droneservice.services;

import com.athanasius.droneservice.dto.DispatchDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.response.DispatchResponse;

public interface DispatchService {
  DispatchResponse setUpDispatch(DispatchDto dispatchDto) throws BadRequestException;
  DispatchResponse triggerDispatch(String droneSerialNumber) throws NotFoundException;
  DispatchResponse getDroneContent(String droneSerialNumber) throws NotFoundException;
  DispatchResponse getDronesByMedication(String medication) throws NotFoundException;
}
