package com.athanasius.droneservice.repositories;

import com.athanasius.droneservice.model.Medication;
import com.athanasius.droneservice.repository.MedicationRepository;
import java.util.List;
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
class MedicationRepositoryTests {

  @Autowired
  MedicationRepository medicationRepository;

  @Test
  public void addNewMedicationTest() {
    Medication drone = new Medication("WEIGHT_1", "Panadol", "image");
    medicationRepository.save(drone);
    List<Medication> d = medicationRepository.findAll();
    Assertions.assertThat(d.size() > 0);
  }

  @Test
  public void deleteFromMedicationTest() {
    medicationRepository.deleteAll();
    Assertions.assertThat(medicationRepository.findAll()).isEmpty();
  }

}
