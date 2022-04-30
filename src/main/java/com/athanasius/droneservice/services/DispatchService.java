package com.athanasius.droneservice.services;

import com.athanasius.droneservice.dto.DispatchDto;
import com.athanasius.droneservice.dto.MedicationDto;
import com.athanasius.droneservice.enums.DroneState;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.response.DispatchResponse;
import com.athanasius.droneservice.response.MedicationResponse;

public interface DispatchService {
  DispatchResponse saveDispatch(DispatchDto dispatchDto) throws BadRequestException;
  DispatchResponse trackADispatch(String trackingId) throws NotFoundException;
  DispatchResponse updateDispatchStatus(DroneState status) throws NotFoundException;
  DispatchResponse getDispatchByStatus(DroneState status) throws NotFoundException;
}
