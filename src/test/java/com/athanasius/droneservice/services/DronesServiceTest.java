package com.athanasius.droneservice.services;

import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.response.DronesResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DronesServiceTest {

  @Autowired
  DronesService dronesService;

  @Test
  void registerDrone(){
    try {
      DronesResponse dronesResponse = dronesService.registerDrone(
          new DronesDto("SNO1234567865", "CRUISER_WEIGHT", 200L, 0.95, "IDLE"));
      Assertions.assertThat(dronesResponse.getStatusCode()).isEqualTo("00");
    }catch (BadRequestException | DuplicateException e){
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
      Assertions.assertThat(e.getStatusCode().equals("04"));
    }
  }

  @Test
  void invalidDronesModelTest(){
    try {
      DronesResponse dronesResponse = dronesService.getDronesByModel("NO_WEIGHT");
      Assertions.assertThat(dronesResponse.getStatusCode().equals("00")).isFalse();
    }catch (BadRequestException | NotFoundException e){
      Assertions.assertThat((e.getStatusCode().equals("05")));
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
      Assertions.assertThat(dronesResponse.getStatusCode().equals("00")).isFalse();
    }catch (BadRequestException | NotFoundException e){
      Assertions.assertThat((e.getStatusCode())).isEqualTo("05");
    }
  }

}
