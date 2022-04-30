package com.athanasius.droneservice.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dispatch {

    @javax.persistence.Id
    @Column(name = "tracking_id", columnDefinition = "VARCHAR(30) NOT NULL")
    private String tranckingId;

    @ManyToOne
    @JoinColumn(name = "serial_no",nullable = false)
    private Drones drone;

    @ManyToOne
    @JoinColumn(name = "code",nullable = false)
    private Medication medication;

    @Column(name = "source", columnDefinition = "VARCHAR(30) NOT NULL")
    private String source;

    @Column(name = "destination", columnDefinition = "VARCHAR(30) NOT NULL")
    private String destination;

    private LocalDateTime timestamp;

}
