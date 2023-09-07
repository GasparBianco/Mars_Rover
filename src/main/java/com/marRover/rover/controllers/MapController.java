package com.marRover.rover.controllers;

import com.marRover.rover.Map;
import com.marRover.rover.Responses.MapResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MapController {
    private Map map;
    private MapResponse mapResponse;
    @GetMapping("/map")
    public ResponseEntity<MapResponse> mapStatus(){
        if (map != null){
            mapResponse.entity=map;
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        }
        mapResponse.exception="Ya existe un mapa";
        return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/map/createDefaultMap")
    public ResponseEntity<MapResponse> mapGeneratorDefault(){
        if (map == null){
            try{
                map = new Map();
                mapResponse.entity = map;
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
        if (map == null){
            try{
                map = new Map(x,y);
                mapResponse.entity = map;
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
        if (map == null){
            mapResponse.exception="No existe un mapa";
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }
        try {
            map.createRandomObstacle();
            mapResponse.entity = map;
            return new ResponseEntity<>(mapResponse, HttpStatus.CREATED);
        }catch (Exception e){
            mapResponse.exception = e.getMessage();
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/map/createCustomObstacle/{x}/{y}")
    public ResponseEntity<MapResponse> createCustomObstacle(@PathVariable int x,@PathVariable int y){
        if (map == null){
            mapResponse.exception="No existe un mapa";
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }
        try{
            map.createCustomObstacle(x,y);
            mapResponse.entity = map;
            return new ResponseEntity<>(mapResponse, HttpStatus.CREATED);
        }catch (Exception e){
            mapResponse.exception = e.getMessage();
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }


    }
    @DeleteMapping("/map/deleteRandomObstacle")
    public ResponseEntity<MapResponse> deleteRandomObstacle(){
        if (map == null){
            mapResponse.exception="No existe un mapa";
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }
        try{
            map.deleteRandomObstacle();
            mapResponse.entity = map;
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        }catch (Exception e){
            mapResponse.exception = e.getMessage();
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/map/deleteCustomObstacle/{x}/{y}")
    public ResponseEntity<MapResponse> deleteCustomObstacle(@PathVariable int x, @PathVariable int y){
        if (map == null){
            mapResponse.exception="No existe un mapa";
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }
        try{
            map.deleteCustomObstacle(x,y);
            mapResponse.entity = map;
            return new ResponseEntity<>(mapResponse, HttpStatus.OK);
        }catch (Exception e){
            mapResponse.exception = e.getMessage();
            return new ResponseEntity<>(mapResponse ,HttpStatus.BAD_REQUEST);
        }
    }
    public Map getMap() {
        return map;
    }
    public void setMapToNull(){
        this.map = null;
        this.mapResponse = null;
    }

}
