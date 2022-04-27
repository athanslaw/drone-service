package com.athanasius.droneservice.repositories;

import com.athanasius.droneservice.enums.DroneModel;
import com.athanasius.droneservice.enums.DroneState;
import com.athanasius.droneservice.model.Drones;
import com.athanasius.droneservice.repository.DronesRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.assertj.core.api.Assertions;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DronesRepositoryTests {

  @Autowired
  DronesRepository dronesRepository;

  @Test
  public void addNewDroneTest() {
    Drones drone = new Drones("SNO1234567865", DroneModel.CRUISER_WEIGHT, 200L, 0.95, DroneState.IDLE);
    dronesRepository.save(drone);
    List<Drones> d = dronesRepository.findAll();
    Assertions.assertThat(d.size() > 0);
  }

  @Test
  public void deleteFromDroneTest() {
    dronesRepository.deleteAll();
    Assertions.assertThat(dronesRepository.findAll()).isEmpty();
  }

  @Test
  public void searchDroneRecordsTest() {
    Drones drone = new Drones("SNO123456765", DroneModel.CRUISER_WEIGHT, 200L, 0.95, DroneState.IDLE);
    Iterable<Drones> drones = dronesRepository.findAll();
    Assertions.assertThat(drones).extracting(Drones::getSerialNo).contains(drone.getSerialNo());
  }

  @Test
  public void getDroneByModelTest() {
    List<Drones> drones = dronesRepository.findByModel(DroneModel.CRUISER_WEIGHT);
    Assertions.assertThat(drones.size()>0);
  }

  @Test
  public void getDroneByStateTest() {
    List<Drones> drones = dronesRepository.findByState(DroneState.IDLE);
    Assertions.assertThat(drones.size()>0);
  }
}
