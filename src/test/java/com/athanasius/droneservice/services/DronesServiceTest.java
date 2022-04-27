package com.athanasius.droneservice.services;

import com.athanasius.droneservice.DroneServiceApplication;
import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.response.DronesResponse;
import com.athanasius.droneservice.services.impl.DronesServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DronesServiceTest {

  @Autowired
  DronesService dronesService;

  @Test
  public void registerDrone(){
    try {
      dronesService.registerDrone(
          new DronesDto("SNO1234567865", "CRUISER_WEIGHT", 200L, 0.95, "IDLE"));
    }catch (BadRequestException | DuplicateException e){}
  }

  @Test
  public void retrieveDrones(){
    DronesResponse dronesResponse = dronesService.retrieveAllDrones();
    Assertions.assertThat(dronesResponse.getDrones().size() > 0);
  }

  @Test
  public void validDronesModelTest(){
    try {
      DronesResponse dronesResponse = dronesService.getDronesByModel("LIGHT_WEIGHT");
      Assertions.assertThat(dronesResponse.getDrones().size() > 0);
    }catch (BadRequestException | NotFoundException e){
      Assertions.assertThat(e.getStatusCode().equals("04"));
    }
  }

  @Test
  public void invalidDronesModelTest(){
    try {
      dronesService.getDronesByModel("NO_WEIGHT");
    }catch (BadRequestException | NotFoundException e){
      Assertions.assertThat((e.getStatusCode().equals("05")));
    }
  }

  @Test
  public void validStateTest(){
    try {
      DronesResponse dronesResponse = dronesService.getDronesByState("IDLE");
      Assertions.assertThat(dronesResponse.getDrones().size() > 0);
    }catch (BadRequestException | NotFoundException e){
      Assertions.assertThat((e.getStatusCode().equals("04")));
    }
  }

  @Test
  public void invalidStateTest(){
    try {
      dronesService.getDronesByState("INVALID");
    }catch (BadRequestException | NotFoundException e){
      Assertions.assertThat((e.getStatusCode().equals("05")));
    }
  }

}
