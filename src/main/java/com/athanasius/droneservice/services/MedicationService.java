package com.athanasius.droneservice.services;

import com.athanasius.droneservice.dto.MedicationDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.response.MedicationResponse;

public interface MedicationService {
  MedicationResponse saveMedication(MedicationDto medicationDto) throws BadRequestException, DuplicateException;
  MedicationResponse retrieveAllMedications();
  MedicationResponse retrieveAllMedicationsByStatus(Boolean status);
  MedicationResponse getMedicationsByCode(String code) throws BadRequestException, NotFoundException;
  MedicationResponse getMedicationByName(String state) throws BadRequestException, NotFoundException;
  MedicationResponse disableMedicationByCode(String code) throws BadRequestException, NotFoundException;
  MedicationResponse enableMedicationByCode(String code) throws BadRequestException, NotFoundException;
}
