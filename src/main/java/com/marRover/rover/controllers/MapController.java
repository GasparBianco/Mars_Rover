package com.marRover.rover.controllers;

import com.marRover.rover.services.MapService;
import com.marRover.rover.Responses.MapResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MapController {
    private MapService mapService;
    private MapResponse mapResponse;
    @GetMapping("/map")
    public ResponseEntity<MapResponse> mapStatus(){
        if (mapService != null){
            mapResponse.entity= mapService;
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        }
        mapResponse.exception="Ya existe un mapa";
        return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/map/createDefaultMap")
    public ResponseEntity<MapResponse> mapGeneratorDefault(){
        if (mapService == null){
            try{
                mapService = new MapService();
                mapResponse.entity = mapService;
                return new ResponseEntity<>(mapResponse, HttpStatus.CREATED);
            }catch (Exception e){
                mapResponse.exception = e.getMessage();
                return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
            }
        }
        mapResponse.exception="No existe un mapa";
        return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/map/createMap/{x}/{y}")
    public ResponseEntity<MapResponse> mapGeneratorCustom(@PathVariable int x,@PathVariable int y){
        if (mapService == null){
            try{
                mapService = new MapService(x,y);
                mapResponse.entity = mapService;
                return new ResponseEntity<>(mapResponse, HttpStatus.CREATED);
            }catch (Exception e){
                mapResponse.exception = e.getMessage();
                return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
            }

        }else {
            mapResponse.exception="No existe un mapa";
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/map/createRandomObstacle")
    public ResponseEntity<MapResponse> obstacleGenerator(){
        if (mapService == null){
            mapResponse.exception="No existe un mapa";
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }
        try {
            mapService.createRandomObstacle();
            mapResponse.entity = mapService;
            return new ResponseEntity<>(mapResponse, HttpStatus.CREATED);
        }catch (Exception e){
            mapResponse.exception = e.getMessage();
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/map/createCustomObstacle/{x}/{y}")
    public ResponseEntity<MapResponse> createCustomObstacle(@PathVariable int x,@PathVariable int y){
        if (mapService == null){
            mapResponse.exception="No existe un mapa";
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }
        try{
            mapService.createCustomObstacle(x,y);
            mapResponse.entity = mapService;
            return new ResponseEntity<>(mapResponse, HttpStatus.CREATED);
        }catch (Exception e){
            mapResponse.exception = e.getMessage();
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }


    }
    @DeleteMapping("/map/deleteRandomObstacle")
    public ResponseEntity<MapResponse> deleteRandomObstacle(){
        if (mapService == null){
            mapResponse.exception="No existe un mapa";
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }
        try{
            mapService.deleteRandomObstacle();
            mapResponse.entity = mapService;
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        }catch (Exception e){
            mapResponse.exception = e.getMessage();
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/map/deleteCustomObstacle/{x}/{y}")
    public ResponseEntity<MapResponse> deleteCustomObstacle(@PathVariable int x, @PathVariable int y){
        if (mapService == null){
            mapResponse.exception="No existe un mapa";
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }
        try{
            mapService.deleteCustomObstacle(x,y);
            mapResponse.entity = mapService;
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        }catch (Exception e){
            mapResponse.exception = e.getMessage();
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }
    }
    public MapService getMap() {
        return mapService;
    }
    public void setMapToNull(){
        this.mapService = null;
        this.mapResponse = null;
    }

}
