package com.athanasius.droneservice.services.impl;

import com.athanasius.droneservice.dto.MedicationDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.model.Medication;
import com.athanasius.droneservice.repository.MedicationRepository;
import com.athanasius.droneservice.response.MedicationResponse;
import com.athanasius.droneservice.services.MedicationService;
import com.athanasius.droneservice.validators.Validator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationServiceImpl implements MedicationService {

  @Autowired
  MedicationRepository medicationRepository;

  @Autowired
  Validator validators;

  @Override
  public MedicationResponse saveMedication(MedicationDto medicationDto)
      throws BadRequestException, DuplicateException {
    validators.validateMedication(medicationDto);
    Optional<Medication> medication = medicationRepository.findById(medicationDto.getCode());
    if(medication.isEmpty()) {
      medicationRepository.save(medicationDto.toModel());
    }else{
      throw new DuplicateException("Record already exists with the submitted code.");
    }
    return new MedicationResponse("00", "Medication added successfully.");
  }

  @Override
  public MedicationResponse retrieveAllMedications()  throws NotFoundException{
    List<Medication> medicationList = medicationRepository.findAll();
    if(medicationList.size() == 0) throw new NotFoundException("No records found");
    return new MedicationResponse("00", "Medications retrieved successfully.", medicationList);
  }

  @Override
  public MedicationResponse retrieveAllMedicationsByStatus(Boolean status) throws NotFoundException{
    List<Medication> medicationList = medicationRepository.findByStatus(status);
    if(medicationList.size() == 0) throw new NotFoundException("No records found");
    return new MedicationResponse("00", "Medications retrieved successfully.", medicationList);
  }

  @Override
  public MedicationResponse getMedicationsByCode(String code)
      throws NotFoundException {
    Optional<Medication> medication = medicationRepository.findById(code);
    if(medication.isEmpty()) throw new NotFoundException("No record found for "+code);
    return new MedicationResponse("00", "Medications retrieved successfully.", medication.get());
  }

  @Override
  public MedicationResponse getMedicationByName(String name)
      throws NotFoundException {
    List<Medication> medications = medicationRepository.findByName(name);
    if(medications.isEmpty()) throw new NotFoundException("No record found for "+name);
    return new MedicationResponse("00", "Medication retrieved successfully.", medications);
  }

  @Override
  public MedicationResponse disableMedicationByCode(String code)
      throws NotFoundException {
    Optional<Medication> medication = medicationRepository.findById(code);
    if(medication.isEmpty()) throw new NotFoundException("No record found for "+code);
    if(!medication.get().getStatus()){
      return new MedicationResponse("00", "Medication is already disabled.", medication.get());
    }
    medication.get().setStatus(false);
    medicationRepository.save(medication.get());
    return new MedicationResponse("00", "Medication disabled successfully.", medication.get());
  }

  @Override
  public MedicationResponse enableMedicationByCode(String code)
      throws NotFoundException {
    Optional<Medication> medication = medicationRepository.findById(code);
    if(medication.isEmpty()) throw new NotFoundException("No record found for "+code);
    if(medication.get().getStatus()){
      return new MedicationResponse("00", "Medication is already enabled.", medication.get());
    }
    medication.get().setStatus(true);
    medicationRepository.save(medication.get());
    return new MedicationResponse("00", "Medication enabled successfully.", medication.get());
  }
}
