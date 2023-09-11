package com.marRover.rover.services;

import com.marRover.rover.services.MapService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class RoverService {
    private final MapService mapService;
    private int x;
    private int y;
    private int facing;

    public RoverService(@NotNull MapService mapService){
        if (mapService.isRover()){
            throw new IllegalCallerException("Solo puede haber un rover");
        }
        int[] coords = mapService.findEmptyCoordinate();
        this.mapService = mapService;
        this.mapService.setRover(true);
        x = coords[0];
        y = coords[1];
        facing = 0;
    }
    public RoverService(@NotNull MapService mapService, int x, int y, int facing){
        this.roverValidations(mapService, x, y);

        this.mapService = mapService;
        this.mapService.setRover(true);
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    private void roverValidations(MapService mapService, int x, int y){
        if (x > mapService.getX() || x < 0 || y < 0 || y > mapService.getY()){
            throw new IllegalArgumentException("Las coordenadas deben estar dentro del mapa");
        }
        if (mapService.isRover()){
            throw new IllegalCallerException("Solo puede haber un rover");
        }
        if(!mapService.checkEmptyObstaclePosition(new int[] {x,y})){
            throw new IllegalArgumentException("Existe un obstaculo en esa coordenada");
        }
    }
    public int[] position() {
        int[] coords = new int[2];
        coords[0] = x;
        coords[1] = y;
        return coords;
    }
    public void input(String command){
        command = command.toLowerCase();

        for (char c : command.toCharArray()) {
            if (c != 'l' && c != 'f' && c != 'b' && c != 'r') {
                throw new IllegalArgumentException("Caracter no permitido: " + c);
            }
            this.executeCommand(c);
        }
    }
    private void executeCommand(Character c){
        switch (c) {
            case 'l' -> this.facing = (this.facing + 3) % 4;
            case 'r' -> this.facing = (this.facing + 1) % 4;
            case 'b' -> this.movement('b');
            case 'f' -> this.movement('f');
        }
    }
    private void movement(Character command){
        if ((command == 'f' && this.facing == 0) || (command == 'b' && this.facing == 2) ){
            int[] new_position = new int[2];
            new_position[0] = x;
            new_position[1] = (y + 1) % mapService.getY();
            if (this.mapService.checkEmptyObstaclePosition(new_position)){

                y = new_position[1];
                return;
            }
        }else if ((command == 'f' && this.facing == 2) || (command == 'b' && this.facing == 0) ){
            int[] new_position = new int[2];
            new_position[0] = x;
            new_position[1] = (y + mapService.getY() - 1) % mapService.getY();
            if (this.mapService.checkEmptyObstaclePosition(new_position)){
                y = new_position[1];
                return;
            }
        }else if ((command == 'f' && this.facing == 1) || (command == 'b' && this.facing == 3) ){
            int[] new_position = new int[2];
            new_position[0] = (x + 1) % mapService.getX();
            new_position[1] = y;
            if (this.mapService.checkEmptyObstaclePosition(new_position)){
                x = new_position[0];
                return;
            }
        }else if ((command == 'f' && this.facing == 3) || (command == 'b' && this.facing == 1) ){
            int[] new_position = new int[2];
            new_position[0] = (x + mapService.getX() - 1) % mapService.getX();
            new_position[1] = y;
            if (this.mapService.checkEmptyObstaclePosition(new_position)){
                x = new_position[0];
                return;
            }
        }
        throw new RuntimeException("Existe un obstaculo en el camino, no se puedo completar el comando");
    }
    public int getFacing(){
        return facing;
    }
    public MapService getMap() {
        return mapService;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
