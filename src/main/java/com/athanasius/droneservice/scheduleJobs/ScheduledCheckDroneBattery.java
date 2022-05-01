package com.athanasius.droneservice.scheduleJobs;

import com.athanasius.droneservice.model.Drones;
import com.athanasius.droneservice.repository.DronesRepository;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ScheduledCheckDroneBattery {
	
	@Autowired
	DronesRepository droneRepository;
	
	static final Logger log = LoggerFactory.getLogger(ScheduledCheckDroneBattery.class);
	
    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        List<Drones> drones = droneRepository.findAll();
        drones.forEach(drone ->
            log.info(String.format("Battery level--: SerialNumber %s Battery Level %s%s",drone.getSerialNo(), drone.getBatteryCapacity(), "%")));
        Thread.sleep(10000);
    }
    
}
