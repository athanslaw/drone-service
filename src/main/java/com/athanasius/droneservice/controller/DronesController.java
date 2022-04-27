package com.athanasius.droneservice.controller;

import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.response.DronesResponse;
import com.athanasius.droneservice.services.DronesService;
import com.athanasius.droneservice.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drones")
public class DronesController {

  @Autowired
  DronesService dronesService;

  @Autowired
  Validator validator;

  @PostMapping(produces = "application/json")
  public ResponseEntity<DronesResponse> postAvailableDroneForLoading(@RequestBody DronesDto dronesDto) {
    try{
      validator.validateDrones(dronesDto);
      DronesResponse dronesResponse = dronesService.registerDrone(dronesDto);
      return ResponseEntity.ok(dronesResponse);
    }catch (DuplicateException e){
      return new ResponseEntity<>(new DronesResponse("09",e.getMessage()), HttpStatus.CONFLICT);
    }catch (BadRequestException e){
      return new ResponseEntity<>(new DronesResponse("05",e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

}
