package com.marRover.rover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapTest {
    @Test
    public void shouldCreateADefaultMapWithXAndYInt(){
        Map map = new Map();
        Assertions.assertNotNull(map.getX());
        Assertions.assertNotNull(map.getY());
    }

    @Test
    public void shouldCreateACustomMapWithXAndYInt(){
        Map map = new Map(5,7);
        Assertions.assertEquals(5, map.getX());
        Assertions.assertEquals(7, map.getY());

        Map map2 = new Map(15,27);
        Assertions.assertEquals(15, map2.getX());
        Assertions.assertEquals(27, map2.getY());
    }
    @Test
    public void xAndYShouldNeverBeEqualsOrLessThanZero() {
        int[][] testData = {
                {0, 1},
                {1, 0},
                {0, 0},
                {-1, 0},
                {0, -1},
                {-1, -2},
                {1, -1},
                {-1, 1}
        };

        for (int[] data : testData) {
            int x = data[0];
            int y = data[1];
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Map(x, y));
        }
    }
    @Test
    public void createRandomObstacleShouldCreateOneNewObstacle(){
        Map map = new Map();
        Assertions.assertEquals(0,map.getObstacles().size());
        map.createRandomObstacle();
        Assertions.assertEquals(1,map.getObstacles().size());
        map.createRandomObstacle();
        Assertions.assertEquals(2,map.getObstacles().size());
    }
    @Test
    public void theObstaclesHaveToBeInsideTheMap(){
        Map map = new Map(1,1);
        map.createRandomObstacle();
        for(int i=0; i<=100; i++){ //This loop is because the obstacle is generated in random position
            Assertions.assertTrue(map.getObstacles().get(0)[0] <= 1 &&
                    map.getObstacles().get(0)[1] <=1 &&
                    map.getObstacles().get(0)[0] >=0 &&
                    map.getObstacles().get(0)[1] >=0);
        }
    }
    @Test
    public void ifMapIsFullOfObstaclesShouldThrowAnException(){
        Map map = new Map(1,1);
        map.createRandomObstacle();
        map.createRandomObstacle();
        map.createRandomObstacle();
        map.createRandomObstacle();
        Assertions.assertThrows(RuntimeException.class, map::createRandomObstacle);
    }
    @Test
    public void createAnObstacleHaveToCreateANewObstacle(){
        Map map = new Map();
        Assertions.assertThrows(NullPointerException.class, () -> {
            map.deleteCustomObstacle(1,1);
        });
        int[] coords = {2,3};
        map.createCustomObstacle(2,3);
        Assertions.assertEquals(1,map.getObstacles().size());
        Assertions.assertArrayEquals(coords, map.getObstacles().get(0));
        Assertions.assertThrows(RuntimeException.class, () -> {
            map.createCustomObstacle(2,3);
        });
    }
    @Test
    public void deleteARandomObstacleShouldDeleteAnObstacle(){
        Map map = new Map();
        Assertions.assertThrows(NullPointerException.class, map::deleteRandomObstacle);
        map.createCustomObstacle(2,3);
        map.deleteRandomObstacle();
        Assertions.assertEquals(0, map.getObstacles().size());
    }
    @Test
    public void deleteCustomObstacle(){
        Map map = new Map();
        Assertions.assertThrows(NullPointerException.class, () -> {
            map.deleteCustomObstacle(1,1);
        });
        map.createCustomObstacle(1,1);
        map.deleteCustomObstacle(1,1);
        Assertions.assertEquals(0,map.getObstacles().size());

    }
    @Test
    public void shouldNotBeAbleToCreateObstaclesWhenRover(){
        Map map = new Map();
        Rover rover = new Rover(map);
        Assertions.assertThrows(IllegalCallerException.class, () -> {
            map.createCustomObstacle(1,1);
        });
        Assertions.assertThrows(IllegalCallerException.class, map::createRandomObstacle);
    }
    @Test
    public void shouldNotBeAbleToDeleteObstaclesWhenRover(){
        Map map = new Map();
        map.createCustomObstacle(2,3);
        Rover rover = new Rover(map);
        Assertions.assertThrows(IllegalCallerException.class, () -> {
            map.deleteCustomObstacle(2,3);
        });
        Assertions.assertThrows(IllegalCallerException.class, map::deleteRandomObstacle);
    }
}
