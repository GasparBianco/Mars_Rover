package com.marRover.rover;

import com.marRover.rover.services.MapService;
import com.marRover.rover.services.RoverService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapEntityServiceTest {
    @Test
    public void shouldCreateADefaultMapWithXAndYInt(){
        MapService mapService = new MapService();
        Assertions.assertNotNull(mapService.getMapSizeX());
        Assertions.assertNotNull(mapService.getMapSizeY());
    }

    @Test
    public void shouldCreateACustomMapWithXAndYInt(){
        MapService mapService = new MapService(5,7);
        Assertions.assertEquals(5, mapService.getMapSizeX());
        Assertions.assertEquals(7, mapService.getMapSizeY());

        MapService mapService2 = new MapService(15,27);
        Assertions.assertEquals(15, mapService2.getMapSizeX());
        Assertions.assertEquals(27, mapService2.getMapSizeY());
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
            Assertions.assertThrows(IllegalArgumentException.class, () -> new MapService(x, y));
        }
    }
    @Test
    public void createRandomObstacleShouldCreateOneNewObstacle(){
        MapService mapService = new MapService();
        Assertions.assertEquals(0, mapService.getObstacles().size());
        mapService.createRandomObstacle();
        Assertions.assertEquals(1, mapService.getObstacles().size());
        mapService.createRandomObstacle();
        Assertions.assertEquals(2, mapService.getObstacles().size());
    }
    @Test
    public void theObstaclesHaveToBeInsideTheMap(){
        MapService mapService = new MapService(1,1);
        mapService.createRandomObstacle();
        for(int i=0; i<=100; i++){ //This loop is because the obstacle is generated in random position
            Assertions.assertTrue(mapService.getObstacles().get(0)[0] <= 1 &&
                    mapService.getObstacles().get(0)[1] <=1 &&
                    mapService.getObstacles().get(0)[0] >=0 &&
                    mapService.getObstacles().get(0)[1] >=0);
        }
    }
    @Test
    public void ifMapIsFullOfObstaclesShouldThrowAnException(){
        MapService mapService = new MapService(1,1);
        mapService.createRandomObstacle();
        mapService.createRandomObstacle();
        mapService.createRandomObstacle();
        mapService.createRandomObstacle();
        Assertions.assertThrows(RuntimeException.class, mapService::createRandomObstacle);
    }
    @Test
    public void createAnObstacleHaveToCreateANewObstacle(){
        MapService mapService = new MapService();
        Assertions.assertThrows(NullPointerException.class, () -> {
            mapService.deleteCustomObstacle(1,1);
        });
        int[] coords = {2,3};
        mapService.createCustomObstacle(2,3);
        Assertions.assertEquals(1, mapService.getObstacles().size());
        Assertions.assertArrayEquals(coords, mapService.getObstacles().get(0));
        Assertions.assertThrows(RuntimeException.class, () -> {
            mapService.createCustomObstacle(2,3);
        });
    }
    @Test
    public void deleteARandomObstacleShouldDeleteAnObstacle(){
        MapService mapService = new MapService();
        Assertions.assertThrows(NullPointerException.class, mapService::deleteRandomObstacle);
        mapService.createCustomObstacle(2,3);
        mapService.deleteRandomObstacle();
        Assertions.assertEquals(0, mapService.getObstacles().size());
    }
    @Test
    public void deleteCustomObstacle(){
        MapService mapService = new MapService();
        Assertions.assertThrows(NullPointerException.class, () -> {
            mapService.deleteCustomObstacle(1,1);
        });
        mapService.createCustomObstacle(1,1);
        mapService.deleteCustomObstacle(1,1);
        Assertions.assertEquals(0, mapService.getObstacles().size());

    }
    @Test
    public void shouldNotBeAbleToCreateObstaclesWhenRover(){
        MapService mapService = new MapService();
        RoverService roverService = new RoverService(mapService);
        Assertions.assertThrows(IllegalCallerException.class, () -> {
            mapService.createCustomObstacle(1,1);
        });
        Assertions.assertThrows(IllegalCallerException.class, mapService::createRandomObstacle);
    }
    @Test
    public void shouldNotBeAbleToDeleteObstaclesWhenRover(){
        MapService mapService = new MapService();
        mapService.createCustomObstacle(2,3);
        RoverService roverService = new RoverService(mapService);
        Assertions.assertThrows(IllegalCallerException.class, () -> {
            mapService.deleteCustomObstacle(2,3);
        });
        Assertions.assertThrows(IllegalCallerException.class, mapService::deleteRandomObstacle);
    }
}
