package com.marRover.rover;

import com.marRover.rover.services.MapService;
import com.marRover.rover.services.RoverService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoverEntityServiceTest {

    @Test
    public void whenRoverIsCreatedHaveToChangeRoverInMapToTrue(){
        MapService mapService = new MapService();
        RoverService roverService = new RoverService(mapService);
        Assertions.assertTrue(mapService.isHasRover());
    }

    @Test
    public void roverHaveToBeCreatedOnARandomLocation(){
        MapService mapService = new MapService();
        for(int i=0;i<25; i++){
            mapService.createRandomObstacle();
        }

        RoverService roverService = new RoverService(mapService);
        Assertions.assertArrayEquals(mapService.findEmptyCoordinate(), roverService.position());
    }
    @Test
    public void roverCouldBeCreateOnACustomLocation(){
        RoverService roverService = new RoverService(new MapService(), 1,1,0);
        Assertions.assertArrayEquals(new int[]{1, 1}, roverService.position());
    }
    @Test
    public void canNotCreateTheRoverOnAObstacle(){
        MapService mapService = new MapService();
        mapService.createCustomObstacle(1,1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RoverService roverService = new RoverService(mapService,1,1,0);
        });
    }
    @Test
    public void justOneRoverCanExist(){
        MapService mapService = new MapService();
        RoverService roverService = new RoverService(mapService);
        Assertions.assertThrows(IllegalCallerException.class, () -> {
            RoverService roverService2 = new RoverService(mapService,1,1,0);
        });
        Assertions.assertThrows(IllegalCallerException.class, () -> {
            RoverService roverService3 = new RoverService(mapService);
        });
    }
    @Test
    public void roverHaveToBeInsideTheMap(){
        MapService mapService = new MapService();
        RoverService roverService = new RoverService(mapService);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RoverService roverService2 = new RoverService(mapService,11,1,0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RoverService roverService2 = new RoverService(mapService,1,11,0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RoverService roverService2 = new RoverService(mapService,-11,1,0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            RoverService roverService2 = new RoverService(mapService,1,-1,0);
        });
    }
    @Test
    public void roverInputOnlyAcceptLRFB() {
        RoverService roverService = new RoverService(new MapService());

        Assertions.assertDoesNotThrow(() -> roverService.input("lrfb"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> roverService.input("abcdefg"));
    }
    @Test
    public void roverInputLShouldDecreaseByOneTheFacing(){
        RoverService roverService = new RoverService(new MapService(), 0, 0, 3);
        roverService.input("l");
        Assertions.assertEquals(2, roverService.getDirection());
        roverService.input("ll");
        Assertions.assertEquals(0, roverService.getDirection());
    }
    @Test
    public void roverInputRShouldIncreaseByOneTheFacing(){
        RoverService roverService = new RoverService(new MapService(), 0, 0, 0);
        roverService.input("r");
        Assertions.assertEquals(1, roverService.getDirection());
        roverService.input("rr");
        Assertions.assertEquals(3, roverService.getDirection());
    }
    @Test
    public void roverFacingAlwaysHaveToBeBetweenZeroAndThree(){
        RoverService roverService = new RoverService(new MapService(), 0, 0, 3);
        roverService.input("l");
        Assertions.assertEquals(2, roverService.getDirection());
        roverService.input("ll");
        Assertions.assertEquals(0, roverService.getDirection());
        roverService.input("l");
        Assertions.assertEquals(3, roverService.getDirection());
        roverService.input("lllll");
        Assertions.assertEquals(2, roverService.getDirection());

        RoverService roverService2 = new RoverService(new MapService(), 0, 0, 3);
        roverService2.input("r");
        Assertions.assertEquals(0, roverService2.getDirection());
        roverService2.input("rr");
        Assertions.assertEquals(2, roverService2.getDirection());
        roverService2.input("r");
        Assertions.assertEquals(3, roverService2.getDirection());
        roverService2.input("rrrrr");
        Assertions.assertEquals(0, roverService2.getDirection());
    }
    @Test
    public void roverMovementForwardNorth(){
        RoverService roverService = new RoverService(new MapService(),3,3,0);
        roverService.input("f");
        Assertions.assertArrayEquals(new int[]{3, 4}, roverService.position());
    }
    @Test
    public void roverMovementForwardEast(){
        RoverService roverService = new RoverService(new MapService(),3,3,1);
        roverService.input("f");
        Assertions.assertArrayEquals(new int[]{4, 3}, roverService.position());
    }
    @Test
    public void roverMovementForwardSouth(){
        RoverService roverService = new RoverService(new MapService(),3,3,2);
        roverService.input("f");
        Assertions.assertArrayEquals(new int[]{3, 2}, roverService.position());
    }
    @Test
    public void roverMovementForwardWest(){
        RoverService roverService = new RoverService(new MapService(),3,3,3);
        roverService.input("f");
        Assertions.assertArrayEquals(new int[]{2, 3}, roverService.position());
    }

    @Test
    public void roverMovementBackwardNorth(){
        RoverService roverService = new RoverService(new MapService(),3,3,0);
        roverService.input("b");
        Assertions.assertArrayEquals(new int[]{3, 2}, roverService.position());
    }
    @Test
    public void roverMovementBackwardEast(){
        RoverService roverService = new RoverService(new MapService(),3,3,1);
        roverService.input("b");
        Assertions.assertArrayEquals(new int[]{2, 3}, roverService.position());
    }
    @Test
    public void roverMovementBackwardSouth(){
        RoverService roverService = new RoverService(new MapService(),3,3,2);
        roverService.input("b");
        Assertions.assertArrayEquals(new int[]{3, 4}, roverService.position());
    }
    @Test
    public void roverMovementBackwardWest(){
        RoverService roverService = new RoverService(new MapService(),3,3,3);
        roverService.input("b");
        Assertions.assertArrayEquals(new int[]{4, 3}, roverService.position());
    }
    @Test
    public void roverShouldNotMoveIntoObstacles(){
        MapService mapService = new MapService();
        mapService.createCustomObstacle(3,3);
        RoverService roverService = new RoverService(mapService, 2,3,1);
        Assertions.assertThrows(RuntimeException.class, () -> roverService.input("f"));
    }
    @Test
    public void roverShouldWrapAroundToOppositeSideWhenExitingTheMap(){
        RoverService roverService = new RoverService(new MapService(1,1),0,0,0);
        roverService.input("ffrffrffrffr");
        Assertions.assertArrayEquals(new int[]{0,0}, roverService.position());
        Assertions.assertEquals(0, roverService.getDirection());
    }
}
