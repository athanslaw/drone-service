package com.athanasius.droneservice.enums;

import com.athanasius.droneservice.exception.BadRequestException;

public enum DroneState {
  IDLE,
  LOADING,
  LOADED,
  DELIVERING,
  DELIVERED,
  RETURNING;


  public static DroneState get(String state) throws BadRequestException {
    DroneState value;
    try {
      value = DroneState.valueOf(state);
    }
    catch(IllegalArgumentException e){
      throw new BadRequestException(
          "Invalid value for Drone State. Valid values are either IDLE, LOADING, LOADED, DELIVERING, DELIVERED or RETURNING");
    }
    return value;
  }
}
