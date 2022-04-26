package com.athanasius.droneservice.repository;

import com.athanasius.droneservice.enums.DroneModel;
import com.athanasius.droneservice.model.Drones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DronesRepository extends JpaRepository<Drones, String> {
    List<Drones> findByModel( DroneModel model);
}
