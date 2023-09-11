package com.marRover.rover.services;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@Service
public class MapService {
    private final int x;
    private final int y;

    private  boolean rover = false;
    private ArrayList<int[]> obstacles = new ArrayList<>();
    public MapService(int x, int y) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("Los valores de x e y deben ser mayores que cero.");
        }
        this.x = x;
        this.y = y;
    }
    public MapService(){
        this.x = 10;
        this.y = 10;
    }
    public void deleteRandomObstacle() {
        if (rover){
            throw new IllegalCallerException("No se pueden editar los obstaculos cuando ya hay un rover desplegado");
        }
        if (obstacles.isEmpty()){
            throw new NullPointerException("No existe ningun obstaculo actualmente");
        }else{
            Random random = new Random();
            int num = random.nextInt(getObstacles().size());
            obstacles.remove(num);
        }
    }
    public void deleteCustomObstacle(int x, int y){
        if (rover){
            throw new IllegalCallerException("No se pueden editar los obstaculos cuando ya hay un rover desplegado");
        }
        int[] coords = new int[2];
        coords[0] = x;
        coords[1] = y;
        for (int[] obstacle : obstacles) {
            if (Arrays.equals(coords, obstacle)) {
                obstacles.remove(obstacle);
                return;
            }
        }
        throw new NullPointerException("No existe ningun obstaculo en las coordenadas otorgadas");
    }
    public void createCustomObstacle(int x, int y) {
        if (rover){
            throw new IllegalCallerException("No se pueden editar los obstaculos cuando ya hay un rover desplegado");
        }
        int[] coords = new int[2];
        coords[0] = x;
        coords[1] = y;
        if (obstacles.isEmpty()){
            obstacles.add(coords);
        }else if(checkEmptyObstaclePosition(coords)){
            obstacles.add(coords);
        }else {
            throw new RuntimeException("Ya existe un obstaculo en esa posicion");
        }
    }
    public void createRandomObstacle(){
        if (rover){
            throw new IllegalCallerException("No se pueden editar los obstaculos cuando ya hay un rover desplegado");
        }
        int [] coords = new int[2];
        Random random = new Random();
        coords[0] = random.nextInt(this.getX()+1);
        coords[1] = random.nextInt(this.getY()+1);
        if (obstacles.isEmpty()){
            obstacles.add(coords);
        }else if(checkEmptyObstaclePosition(coords)){
            obstacles.add(coords);
        }else {
            obstacles.add(findEmptyCoordinate());
        }
    }

    public boolean checkEmptyObstaclePosition(int[] coords){
        boolean check = true;
        for (int[] obstacle : obstacles) {
            if (Arrays.equals(coords, obstacle)) {
                check = false;
                break;
            }
        }
        return check;
    }

    public int[] findEmptyCoordinate() {
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                int[] coords = {i, j};
                if (this.checkEmptyObstaclePosition(coords)) {
                    return coords;
                }
            }
        }
        throw new RuntimeException("El mapa esta lleno de obstaculos");
    }

    public ArrayList<int[]> getObstacles() {
        return obstacles;
    }

    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }
    public boolean isRover() {
        return rover;
    }

    public void setRover(boolean rover) {
        this.rover = rover;
    }
}
