package com.athanasius.droneservice.services;

import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.enums.DroneModel;
import com.athanasius.droneservice.enums.DroneState;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.response.DronesResponse;
import com.athanasius.droneservice.services.impl.DronesServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DronesServiceTest {

  @Autowired
  DronesServiceImpl dronesService;

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
    Assertions.assertThat(dronesResponse.getDrones().size() > -1);
  }

}
