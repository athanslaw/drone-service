package com.athanasius.droneservice.repositories;

import com.athanasius.droneservice.enums.DroneModel;
import com.athanasius.droneservice.enums.DroneState;
import com.athanasius.droneservice.model.Dispatch;
import com.athanasius.droneservice.model.Drones;
import com.athanasius.droneservice.model.Medication;
import com.athanasius.droneservice.repository.DispatchRepository;
import com.athanasius.droneservice.repository.DronesRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
class DispatchRepositoryTests {

  @Autowired
  DispatchRepository dispatchRepository;

  @Test
  public void addNewDispatchTest() {
    Drones drone = new Drones();
    drone.setSerialNo("SNO1234567862");
    Medication medication = new Medication();
    medication.setCode("SQO122");
    Dispatch dispatch = new Dispatch("tracker1", drone, medication, "Lagos Nigeria","Canada", LocalDateTime.now());

    dispatchRepository.save(dispatch);
    Optional<Dispatch> d = dispatchRepository.findById("tracker1");
    Assertions.assertThat(d.isEmpty()).isFalse();
  }

  @Test
  public void deleteFromDispatchTest() {
    dispatchRepository.deleteAll();
    Assertions.assertThat(dispatchRepository.findAll()).isEmpty();
  }

  @Test
  public void retrieveDispatchRecordsByDroneTest() {
    Drones drone = new Drones("SNO123456765", DroneModel.CRUISER_WEIGHT, 200L, 0.95, DroneState.IDLE);
    List<Dispatch> dispatch = dispatchRepository.findByDrone(drone);
    Assertions.assertThat(dispatch.size()>0).isTrue();
  }

  @Test
  public void retrieveDispatchRecordsByMedicationTest() {
    Medication medication = new Medication("MED1", 200, "Panadol", "athans", true);
    List<Dispatch> dispatch = dispatchRepository.findByMedication(medication);
    Assertions.assertThat(dispatch.size()>0).isTrue();
  }

}
