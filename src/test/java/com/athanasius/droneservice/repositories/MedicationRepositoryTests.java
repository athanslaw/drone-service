package com.athanasius.droneservice.repositories;

import com.athanasius.droneservice.model.Medication;
import com.athanasius.droneservice.repository.MedicationRepository;
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
class MedicationRepositoryTests {

  @Autowired
  MedicationRepository medicationRepository;

  @Test
  public void addNewMedicationTest() {
    Medication medication = new Medication("MED1", 200, "Panadol", "athans", true);
    medicationRepository.save(medication);
    Optional<Medication> d = medicationRepository.findById("MED1");
    Assertions.assertThat(d.isEmpty()).isFalse();
  }

  @Test
  public void deleteFromMedicationTest() {
    medicationRepository.deleteAll();
    Assertions.assertThat(medicationRepository.findAll()).isEmpty();
  }

}
