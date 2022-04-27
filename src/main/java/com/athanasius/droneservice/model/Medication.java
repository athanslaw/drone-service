package com.athanasius.droneservice.model;

import com.athanasius.droneservice.enums.DroneModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

    @javax.persistence.Id
    @Column(name = "code", columnDefinition = "VARCHAR(20) NOT NULL")
    private String code;

    @Column(name = "weight", columnDefinition = "INT(20) NOT NULL")
    private Integer weight;

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;

    // base64 image or image path
    @Column(name = "image", columnDefinition = "VARCHAR(240) NOT NULL")
    private String image;

    // status is either true for active or false for inactive. This shows available medications that can be loaded
    private Boolean status;

}
