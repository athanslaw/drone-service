package com.athanasius.droneservice.model;

import com.athanasius.droneservice.enums.DroneModel;
import com.athanasius.droneservice.enums.DroneState;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Medication {

    @javax.persistence.Id
    @Column(name = "weight_code", columnDefinition = "VARCHAR(20) NOT NULL")
    private String weightCode;

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL")
    private DroneModel name;

    // base64 image
    @Column(name = "image", columnDefinition = "VARCHAR(240) NOT NULL")
    private Long image;

}
