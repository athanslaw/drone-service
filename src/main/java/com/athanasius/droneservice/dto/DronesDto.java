package com.athanasius.droneservice.dto;

import com.athanasius.droneservice.enums.DroneModel;
import com.athanasius.droneservice.enums.DroneState;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.model.Drones;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DronesDto {

  private String serialNo;

  private String model;

  private Long weightLimit;

  private Double batteryCapacity;

  private String state;

  public Drones toModel() throws BadRequestException {
    return new Drones(this.getSerialNo(), DroneModel.get(this.getModel()), this.getWeightLimit(), this.getBatteryCapacity(), DroneState.get(this.getState()));
  }

}
