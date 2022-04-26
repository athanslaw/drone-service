package com.athanasius.droneservice.model;

import com.athanasius.droneservice.enums.DroneModel;
import com.athanasius.droneservice.enums.DroneState;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drones{

    @javax.persistence.Id
    @Column(name = "serial_no", columnDefinition = "VARCHAR(110) NOT NULL")
    private String serialNo;

    // used Enums to manage the model with the assumption that the ordering of the model will not change
    @Enumerated(EnumType.ORDINAL)
    private DroneModel model;

    @Column(name = "weight_limit", columnDefinition = "VARCHAR(6) NOT NULL")
    private Long weightLimit;

    private Double batteryCapacity;

    // used Enums to handle the states with the assumption that the order of the states will not change: IDLE, LOADING, LOADED, DELIVERING,
    @Enumerated(EnumType.ORDINAL)
    private DroneState state;

}
