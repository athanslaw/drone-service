package com.athanasius.droneservice.dto;

import com.athanasius.droneservice.model.Medication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDto {

  private String weightCode;

  private String name;

  private String image;

  public Medication toMedication() {
    return new Medication(this.getWeightCode(), this.getName(), this.getImage());
  }

}
