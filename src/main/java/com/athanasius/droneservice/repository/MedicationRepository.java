package com.athanasius.droneservice.repository;

import com.athanasius.droneservice.model.Medication;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String> {
  List<Medication> findByStatus(boolean status);
  List<Medication> findByName(String name);
}
