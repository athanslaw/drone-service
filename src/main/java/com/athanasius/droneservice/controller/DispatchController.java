package com.athanasius.droneservice.controller;

import com.athanasius.droneservice.dto.DispatchDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.response.DispatchResponse;
import com.athanasius.droneservice.response.DronesResponse;
import com.athanasius.droneservice.services.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dispatch")
public class DispatchController {

  @Autowired
  DispatchService dispatchService;

  @GetMapping(path= "/health", produces = "application/json")
  public String health() {
    return "Health check";
  }

  @PostMapping(path= "/setup", produces = "application/json")
  public ResponseEntity<DispatchResponse> setUpDispatch(@RequestBody DispatchDto dispatchDto) {
    try{
      DispatchResponse dispatchResponse = dispatchService.setUpDispatch(dispatchDto);
      return new ResponseEntity<>(dispatchResponse, HttpStatus.CREATED);
    }catch (BadRequestException e){
      return new ResponseEntity<>(new DispatchResponse(e.getStatusCode(),e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(path= "/trigger/{droneSerialNo}", produces = "application/json")
  public ResponseEntity<DispatchResponse> setUpDispatch(@PathVariable String droneSerialNo) {
    try{
      DispatchResponse dispatchResponse = dispatchService.triggerDispatch(droneSerialNo);
      return new ResponseEntity<>(dispatchResponse, HttpStatus.OK);
    }catch (NotFoundException ne){
      return new ResponseEntity<>(new DispatchResponse(ne.getStatusCode(),ne.getMessage()), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path= "/drone/{droneSerialNo}", produces = "application/json")
  public ResponseEntity<DispatchResponse> viewDroneContent(@PathVariable String droneSerialNo) {
    try{
      DispatchResponse dispatchResponse = dispatchService.getDroneContent(droneSerialNo);
      return new ResponseEntity<>(dispatchResponse, HttpStatus.OK);
    }catch (NotFoundException ne){
      return new ResponseEntity<>(new DispatchResponse(ne.getStatusCode(),ne.getMessage()), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path= "/medication/{medicationCode}", produces = "application/json")
  public ResponseEntity<DispatchResponse> viewDroneByMedication(@PathVariable String medicationCode) {
    try{
      DispatchResponse dispatchResponse = dispatchService.getDronesByMedication(medicationCode);
      return new ResponseEntity<>(dispatchResponse, HttpStatus.OK);
    }catch (NotFoundException ne){
      return new ResponseEntity<>(new DispatchResponse(ne.getStatusCode(),ne.getMessage()), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path= "/available-drones", produces = "application/json")
  public ResponseEntity<DronesResponse> viewAvailableDronesForLoading() {
    DronesResponse dronesResponse = dispatchService.getAvailableDrones();
    return new ResponseEntity<>(dronesResponse, HttpStatus.OK);
  }
  // consider battery level

}
