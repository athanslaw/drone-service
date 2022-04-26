package com.athanasius.droneservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dispatch/api/v1/")
public class DispatchController {

  @GetMapping(path= "/health", produces = "application/json")
  public String getAvailableDroneForLoading() {
    return "Health check";
  }

}
