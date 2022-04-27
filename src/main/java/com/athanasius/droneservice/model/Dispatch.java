package com.athanasius.droneservice.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dispatch {

    @javax.persistence.Id
    @Column(name = "tracking_id", columnDefinition = "VARCHAR(110) NOT NULL")
    private String tranckingId;

    @Column(name = "drone_serial_no", columnDefinition = "VARCHAR(110) NOT NULL")
    private Long droneSerialNo;

    @Column(name = "medication_code", columnDefinition = "VARCHAR(20) NOT NULL")
    private Long medicationCode;

    @Column(name = "source", columnDefinition = "VARCHAR(30) NOT NULL")
    private Long source;

    @Column(name = "destination", columnDefinition = "VARCHAR(30) NOT NULL")
    private Long destination;

    private LocalDateTime timestamp;

}
