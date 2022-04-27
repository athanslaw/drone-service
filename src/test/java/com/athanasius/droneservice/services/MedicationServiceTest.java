package com.athanasius.droneservice.services;

import com.athanasius.droneservice.dto.MedicationDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.response.MedicationResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MedicationServiceTest {

  @Autowired
  MedicationService medicationService;

  @Test
  void saveMedicationTest(){
    try {
      MedicationResponse medicationResponse = medicationService.saveMedication(new MedicationDto("MED1", 200, "Panadol", "athans.jpg"));
      Assertions.assertThat(medicationResponse.getCount() > 0).isTrue();
    }catch (BadRequestException | DuplicateException e){
      Assertions.assertThat(e.getStatusCode()).isIn("05 09");
    }
  }

  @Test
  void retrieveAllMedicationsTest(){
    try {
      MedicationResponse medicationResponse = medicationService.retrieveAllMedications();
      Assertions.assertThat(medicationResponse.getCount() > 0).isTrue();
    }catch (NotFoundException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("04");
    }
  }

  @Test
  void retrieveActiveMedicationsTest(){
    try {
      MedicationResponse medicationResponse = medicationService.retrieveAllMedicationsByStatus(true);
      Assertions.assertThat(medicationResponse.getCount() > 0).isTrue();
    }catch (NotFoundException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("04");
    }
  }

  @Test
  void retrieveInactiveMedicationsTest(){
    try {
      MedicationResponse medicationResponse = medicationService.retrieveAllMedicationsByStatus(false);
      Assertions.assertThat(medicationResponse.getCount() > 0).isTrue();
    }catch (NotFoundException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("04");
    }
  }

  @Test
  void findMedicationsByCodeTest(){
    try {
      MedicationResponse medicationResponse = medicationService.getMedicationsByCode("MED1");
      Assertions.assertThat(medicationResponse.getCount()).isEqualTo(1);
    }catch (NotFoundException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("04");
    }
  }

  @Test
  void findMedicationsByNameTest(){
    try {
      MedicationResponse medicationResponse = medicationService.getMedicationByName("MED1");
      Assertions.assertThat(medicationResponse.getCount()).isEqualTo(1);
    }catch (NotFoundException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("04");
    }
  }

  @Test
  void disableMedicationsTest(){
    try {
      MedicationResponse medicationResponse = medicationService.disableMedicationByCode("MED1");
      Assertions.assertThat(medicationResponse.getCount()).isEqualTo(1);
    }catch (NotFoundException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("04");
    }
  }

  @Test
  void enableMedicationsTest(){
    try {
      MedicationResponse medicationResponse = medicationService.enableMedicationByCode("MED1");
      Assertions.assertThat(medicationResponse.getCount()).isEqualTo(1);
    }catch (NotFoundException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("04");
    }
  }

}
