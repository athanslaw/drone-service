package com.athanasius.droneservice.services;

import com.athanasius.droneservice.dto.DispatchDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.response.DispatchResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DispatchServiceTest {

  @Autowired
  DispatchService dispatchService;

  @Test
  void setupDispatchTest(){
    try {
      DispatchResponse dispatchResponse = dispatchService.setUpDispatch(
          new DispatchDto("SMO1234567862", "SQO122", "Ikeja, Lagos", "Canada"));

      Assertions.assertThat(dispatchResponse.getStatusCode()).isEqualTo("00");
    }catch (BadRequestException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("05");
    }
  }

  @Test
  void triggerDispatchTest(){
    try {
      DispatchResponse dispatchResponse = dispatchService.triggerDispatch("tracker1224");
      Assertions.assertThat(dispatchResponse.getStatusCode()).isEqualTo("00");
    }catch (NotFoundException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("04");
    }
  }

  @Test
  void viewDroneContentTest(){
    try {
      DispatchResponse dispatchResponse = dispatchService.getDroneContent("tracker1224");
      Assertions.assertThat(dispatchResponse.getStatusCode()).isEqualTo("00");
    }catch (NotFoundException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("04");
    }
  }

  @Test
  void viewDronesMedicationCodeTest(){
    try {
      DispatchResponse dispatchResponse = dispatchService.getDronesByMedication("MED1");
      Assertions.assertThat(dispatchResponse.getStatusCode()).isEqualTo("00");
    }catch (NotFoundException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("04");
    }
  }

}
