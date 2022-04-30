package com.athanasius.droneservice.services;

import com.athanasius.droneservice.dto.DispatchDto;
import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.enums.DroneState;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.model.Drones;
import com.athanasius.droneservice.model.Medication;
import com.athanasius.droneservice.response.DispatchResponse;
import com.athanasius.droneservice.response.DronesResponse;
import java.time.LocalDateTime;
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
    }catch (BadRequestException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("05");
    }
  }

  /*
  @Test
  void updateDroneStatus(){
    try {
      dronesService.updateDroneState("SNO1234567865", DroneState.IDLE);
    }catch (NotFoundException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("09");
    }
  }

  @Test
  void retrieveDrones(){
    DronesResponse dronesResponse = dronesService.retrieveAllDrones();
    Assertions.assertThat(dronesResponse.getStatusCode()).isEqualTo("00");
  }

  @Test
  void validDronesModelTest(){
    try {
      DronesResponse dronesResponse = dronesService.getDronesByModel("LIGHT_WEIGHT");
      Assertions.assertThat(dronesResponse.getStatusCode()).isEqualTo("00");
    }catch (BadRequestException | NotFoundException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("04");
    }
  }

  @Test
  void invalidDronesModelTest(){
    try {
      DronesResponse dronesResponse = dronesService.getDronesByModel("NO_WEIGHT");
      Assertions.assertThat(dronesResponse.getStatusCode()).isNotEqualTo("00");
    }catch (BadRequestException | NotFoundException e){
      Assertions.assertThat(e.getStatusCode()).isEqualTo("05");
    }
  }

  @Test
  void validStateTest(){
    try {
      DronesResponse dronesResponse = dronesService.getDronesByState("IDLE");
      Assertions.assertThat(dronesResponse.getStatusCode()).isEqualTo("00");
    }catch (BadRequestException | NotFoundException e){
      Assertions.assertThat((e.getStatusCode())).isEqualTo("04");
    }
  }

  @Test
  void invalidStateTest(){
    try {
      DronesResponse dronesResponse = dronesService.getDronesByState("INVALID");
      Assertions.assertThat(dronesResponse.getStatusCode()).isNotEqualTo("00");
    }catch (BadRequestException | NotFoundException e){
      Assertions.assertThat((e.getStatusCode())).isEqualTo("05");
    }
  }
*/
}
