package com.marRover.rover;

import org.jetbrains.annotations.NotNull;

public class Rover {
    private final Map map;
    private int x;
    private int y;
    private int facing;

    public Rover(@NotNull Map map){
        if (map.isRover()){
            throw new IllegalCallerException("Solo puede haber un rover");
        }
        int[] coords = map.findEmptyCoordinate();
        this.map = map;
        this.map.setRover(true);
        x = coords[0];
        y = coords[1];
        facing = 0;
    }
    public Rover(@NotNull Map map, int x, int y, int facing){
        this.roverValidations(map, x, y);

        this.map = map;
        this.map.setRover(true);
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    private void roverValidations(Map map, int x, int y){
        if (x > map.getX() || x < 0 || y < 0 || y > map.getY()){
            throw new IllegalArgumentException("Las coordenadas deben estar dentro del mapa");
        }
        if (map.isRover()){
            throw new IllegalCallerException("Solo puede haber un rover");
        }
        if(!map.checkEmptyObstaclePosition(new int[] {x,y})){
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
            new_position[1] = (y + 1) % map.getY();
            if (this.map.checkEmptyObstaclePosition(new_position)){

                y = new_position[1];
                return;
            }
        }else if ((command == 'f' && this.facing == 2) || (command == 'b' && this.facing == 0) ){
            int[] new_position = new int[2];
            new_position[0] = x;
            new_position[1] = (y + map.getY() - 1) % map.getY();
            if (this.map.checkEmptyObstaclePosition(new_position)){
                y = new_position[1];
                return;
            }
        }else if ((command == 'f' && this.facing == 1) || (command == 'b' && this.facing == 3) ){
            int[] new_position = new int[2];
            new_position[0] = (x + 1) % map.getX();
            new_position[1] = y;
            if (this.map.checkEmptyObstaclePosition(new_position)){
                x = new_position[0];
                return;
            }
        }else if ((command == 'f' && this.facing == 3) || (command == 'b' && this.facing == 1) ){
            int[] new_position = new int[2];
            new_position[0] = (x + map.getX() - 1) % map.getX();
            new_position[1] = y;
            if (this.map.checkEmptyObstaclePosition(new_position)){
                x = new_position[0];
                return;
            }
        }
        throw new RuntimeException("Existe un obstaculo en el camino, no se puedo completar el comando");
    }
    public int getFacing(){
        return facing;
    }
    public Map getMap() {
        return map;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
