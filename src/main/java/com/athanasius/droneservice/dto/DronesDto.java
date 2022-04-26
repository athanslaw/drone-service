package com.athanasius.droneservice.dto;

import com.athanasius.droneservice.enums.DroneModel;
import com.athanasius.droneservice.enums.DroneState;
import com.athanasius.droneservice.model.Drones;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DronesDto {

  private String serialNo;

  private DroneModel model;

  private Long weightLimit;

  private Double batteryCapacity;

  private DroneState state;

  public Drones toModel(){
    System.out.println("SerialNo:"+this.serialNo);
    return new Drones(this.getSerialNo(), this.getModel(), this.getWeightLimit(), this.getBatteryCapacity(), this.getState());
  }

}
