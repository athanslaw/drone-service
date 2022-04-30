package com.athanasius.droneservice.repository;

import com.athanasius.droneservice.model.Dispatch;
import com.athanasius.droneservice.model.Drones;
import com.athanasius.droneservice.model.Medication;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchRepository extends JpaRepository<Dispatch, String> {
    List<Dispatch> findByDrone( Drones drone);
    List<Dispatch> findByMedication( Medication medication);
}
