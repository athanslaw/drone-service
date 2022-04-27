package com.athanasius.droneservice.services;

import com.athanasius.droneservice.dto.MedicationDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.response.MedicationResponse;

public interface MedicationService {
  MedicationResponse saveMedication(MedicationDto medicationDto) throws BadRequestException, DuplicateException;
  MedicationResponse retrieveAllMedications() throws NotFoundException;
  MedicationResponse retrieveAllMedicationsByStatus(Boolean status) throws NotFoundException;
  MedicationResponse getMedicationsByCode(String code) throws NotFoundException;
  MedicationResponse getMedicationByName(String state) throws NotFoundException;
  MedicationResponse disableMedicationByCode(String code) throws NotFoundException;
  MedicationResponse enableMedicationByCode(String code) throws NotFoundException;
}
