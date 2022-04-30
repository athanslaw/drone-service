package com.athanasius.droneservice.dto;

import com.athanasius.droneservice.model.Dispatch;
import com.athanasius.droneservice.model.Drones;
import com.athanasius.droneservice.model.Medication;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DispatchDto {

  private String id;

  private String droneSerialNo;

  private String medicationCode;

  private LocalDateTime timestamp;

  private Drones drone;

  private Medication medication;
  public Dispatch toModel() {
    return new Dispatch(this.getId(), this.getDrone(), this.getMedication(), this.getTimestamp());
  }

  public DispatchDto(String droneSerialNo, String medicationCode) {
    this.droneSerialNo = droneSerialNo;
    this.medicationCode = medicationCode;
  }
}
