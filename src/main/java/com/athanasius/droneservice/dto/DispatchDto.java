package com.athanasius.droneservice.dto;

import com.athanasius.droneservice.model.Dispatch;
import com.athanasius.droneservice.model.Drones;
import com.athanasius.droneservice.model.Medication;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DispatchDto {

  private String trackingId;

  private String droneSerialNo;

  private String medicationCode;

  private String source;

  private String destination;

  private LocalDateTime timestamp;

  private Drones drone;

  private Medication medication;
  public Dispatch toModel() {
    return new Dispatch(this.getTrackingId(), this.getDrone(), this.getMedication(), this.getSource(), this.getDestination(), this.getTimestamp());
  }

  public DispatchDto(String droneSerialNo, String medicationCode, String source,
      String destination) {
    this.droneSerialNo = droneSerialNo;
    this.medicationCode = medicationCode;
    this.source = source;
    this.destination = destination;
  }
}
