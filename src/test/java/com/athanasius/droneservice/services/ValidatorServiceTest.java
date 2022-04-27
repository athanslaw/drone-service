package com.athanasius.droneservice.services;

import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.dto.MedicationDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.response.MedicationResponse;
import com.athanasius.droneservice.validators.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ValidatorServiceTest {

  @Autowired
  Validator validator;

  @Test
  void droneValidationTest(){
    try {
      validator.validateDrones(new DronesDto("SNO1234567865", "CRUISER_WEIGHT", 200L, 0.95, "IDLE"));
    }catch (BadRequestException e){
      Assertions.assertThat(e.getStatusCode()).isIn("05");
    }
  }

  @Test
  void medicationValidationTest(){
    try {
      validator.validateMedication(new MedicationDto("MED1", 200, "Panadol", "athans.jpg"));
    }catch (BadRequestException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("05");
    }
  }

}
