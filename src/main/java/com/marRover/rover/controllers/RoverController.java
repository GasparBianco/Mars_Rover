package com.marRover.rover.controllers;

import com.marRover.rover.Map;
import com.marRover.rover.Rover;
import com.marRover.rover.Responses.RoverResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
public class RoverController {
    private Rover rover;
    private RoverResponse roverResponse;
    private final MapController mapController;

    public RoverController(MapController mapController) {
        this.mapController = mapController;
    }
    @GetMapping("/rover")
    public ResponseEntity<RoverResponse> roverStatus(){
        if (rover!=null){
            roverResponse.entity = rover;
            return new ResponseEntity<>(roverResponse, HttpStatus.OK);
        }else{
            roverResponse.exception = "No existe ningun rover";
            return new ResponseEntity<>(roverResponse ,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/rover/createRover")
    public ResponseEntity<RoverResponse> createDefaultRover(){
        Map map = mapController.getMap();
        RoverResponse roverResponse = new RoverResponse();
        if (rover == null && map!=null){
            try {
                rover = new Rover(map);
                roverResponse.entity = rover;
                return new ResponseEntity<>(roverResponse, HttpStatus.CREATED);
            }catch(Exception e){
                roverResponse.exception = e.getMessage();
                return new ResponseEntity<>(roverResponse ,HttpStatus.BAD_REQUEST);
            }
        }else{
            roverResponse.exception = "No se a podido crear un rover. " +
                    "Ya existe un rover o no se a creado un mapa.";
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/rover/createCustomRover/{x}/{y}/{facing}")
    public ResponseEntity<RoverResponse> createCustomRover(@PathVariable int x, @PathVariable int y, @PathVariable int facing){
        Map map = mapController.getMap();
        RoverResponse roverResponse = new RoverResponse();
        if (rover == null && map!=null){
            try{
                rover = new Rover(map,x,y,facing);
                roverResponse.entity = rover;
                return new ResponseEntity<>(roverResponse, HttpStatus.CREATED);
            }catch(Exception e){
                roverResponse.exception = e.getMessage();
                return new ResponseEntity<>(roverResponse, HttpStatus.BAD_REQUEST);
            }
        }else{
            roverResponse.exception = "No se a podido crear un rover. " +
                    "Ya existe un rover o no se a creado un mapa.";
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/rover/input/{input}")
    public ResponseEntity<RoverResponse> sendInputToRover(@PathVariable String input){
        if (rover!=null){
            rover.input(input);
            roverResponse.entity = rover;
            return new ResponseEntity<>(roverResponse, HttpStatus.OK);
        }else{
            roverResponse.exception = "No existe ningun rover.";
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/reset")
    public HttpStatus reset(){
        this.rover = null;
        roverResponse = null;
        mapController.setMapToNull();
        return HttpStatus.OK;
    }
}
