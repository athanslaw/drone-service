package com.athanasius.droneservice.controller;

import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.response.DronesResponse;
import com.athanasius.droneservice.services.impl.DronesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dispatch/api/v1")
public class DispatchController {

  @GetMapping(path= "/health", produces = "application/json")
  public String health() {
    return "Health check";
  }

}
