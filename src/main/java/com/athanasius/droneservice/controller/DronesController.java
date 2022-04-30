package com.athanasius.droneservice.controller;

import com.athanasius.droneservice.dto.DronesDto;
import com.athanasius.droneservice.exception.BadRequestException;
import com.athanasius.droneservice.exception.DuplicateException;
import com.athanasius.droneservice.exception.NotFoundException;
import com.athanasius.droneservice.response.DronesResponse;
import com.athanasius.droneservice.services.DronesService;
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
@RequestMapping("/drones")
public class DronesController {

  @Autowired
  DronesService dronesService;

  @PostMapping(produces = "application/json")
  public ResponseEntity<DronesResponse> postAvailableDroneForLoading(@RequestBody DronesDto dronesDto) {
    try{
      DronesResponse dronesResponse = dronesService.registerDrone(dronesDto);
      return new ResponseEntity<>(dronesResponse, HttpStatus.CREATED);
    }catch (DuplicateException e){
      return new ResponseEntity<>(new DronesResponse(e.getStatusCode(),e.getMessage()), HttpStatus.CONFLICT);
    }catch (BadRequestException e){
      return new ResponseEntity<>(new DronesResponse(e.getStatusCode(),e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(path= "/model/{model}", produces = "application/json")
  public ResponseEntity<DronesResponse> getDronesByModel(@PathVariable("model") String model) {
    try{
      return ResponseEntity.ok(dronesService.getDronesByModel(model));
    }
    catch (BadRequestException e){
      return new ResponseEntity<>(new DronesResponse(e.getStatusCode(),e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    catch (NotFoundException e){
      return new ResponseEntity<>(new DronesResponse(e.getStatusCode(),e.getMessage()), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path= "/state/{state}", produces = "application/json")
  public ResponseEntity<DronesResponse> getDronesByState(@PathVariable("state") String state) {
    try{
      return ResponseEntity.ok(dronesService.getDronesByState(state));
    }
    catch (BadRequestException e){
      return new ResponseEntity<>(new DronesResponse(e.getStatusCode(),e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    catch (NotFoundException e){
      return new ResponseEntity<>(new DronesResponse(e.getStatusCode(),e.getMessage()), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path= "/idle", produces = "application/json")
  public ResponseEntity<DronesResponse> getDronesByState() {
    try{
      return ResponseEntity.ok(dronesService.getIdleDrones());
    }
    catch (NotFoundException e){
      return new ResponseEntity<>(new DronesResponse(e.getStatusCode(),e.getMessage()), HttpStatus.NOT_FOUND);
    }
  }

}
