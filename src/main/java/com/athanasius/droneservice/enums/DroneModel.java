package com.athanasius.droneservice.enums;

import com.athanasius.droneservice.exception.BadRequestException;

public enum DroneModel {
  LIGHT_WEIGHT,
  MIDDLE_WEIGHT,
  CRUISER_WEIGHT,
  HEAVY_WEIGHT;

  public static DroneModel get(String model) throws BadRequestException {
    DroneModel value;
    try {
      value = DroneModel.valueOf(model);
    }
    catch(IllegalArgumentException | NullPointerException e){
      throw new BadRequestException(
          "Invalid value for Model. Valid values are either LIGHT_WEIGHT, MIDDLE_WEIGHT, CRUISER_WEIGHT or HEAVY_WEIGHT");
    }
    return value;
  }

  }
