package com.marRover.rover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoverTest {

    @Test
    public void whenRoverIsCreatedHaveToChangeRoverInMapToTrue(){
        Map map = new Map();
        Rover rover = new Rover(map);
        Assertions.assertTrue(map.isRover());
    }

    @Test
    public void roverHaveToBeCreatedOnARandomLocation(){
        Map map = new Map();
        for(int i=0;i<25; i++){
            map.createRandomObstacle();
        }

        Rover rover = new Rover(map);
        Assertions.assertArrayEquals(map.findEmptyCoordinate(), rover.position());
    }
    @Test
    public void roverCouldBeCreateOnACustomLocation(){
        Rover rover = new Rover(new Map(), 1,1,0);
        Assertions.assertArrayEquals(new int[]{1, 1}, rover.position());
    }
    @Test
    public void canNotCreateTheRoverOnAObstacle(){
        Map map = new Map();
        map.createCustomObstacle(1,1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rover rover = new Rover(map,1,1,0);
        });
    }
    @Test
    public void justOneRoverCanExist(){
        Map map = new Map();
        Rover rover = new Rover(map);
        Assertions.assertThrows(IllegalCallerException.class, () -> {
            Rover rover2 = new Rover(map,1,1,0);
        });
        Assertions.assertThrows(IllegalCallerException.class, () -> {
            Rover rover3 = new Rover(map);
        });
    }
    @Test
    public void roverHaveToBeInsideTheMap(){
        Map map = new Map();
        Rover rover = new Rover(map);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rover rover2 = new Rover(map,11,1,0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rover rover2 = new Rover(map,1,11,0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rover rover2 = new Rover(map,-11,1,0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Rover rover2 = new Rover(map,1,-1,0);
        });
    }
    @Test
    public void roverInputOnlyAcceptLRFB() {
        Rover rover = new Rover(new Map());

        Assertions.assertDoesNotThrow(() -> rover.input("lrfb"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> rover.input("abcdefg"));
    }
    @Test
    public void roverInputLShouldDecreaseByOneTheFacing(){
        Rover rover = new Rover(new Map(), 0, 0, 3);
        rover.input("l");
        Assertions.assertEquals(2, rover.getFacing());
        rover.input("ll");
        Assertions.assertEquals(0, rover.getFacing());
    }
    @Test
    public void roverInputRShouldIncreaseByOneTheFacing(){
        Rover rover = new Rover(new Map(), 0, 0, 0);
        rover.input("r");
        Assertions.assertEquals(1, rover.getFacing());
        rover.input("rr");
        Assertions.assertEquals(3, rover.getFacing());
    }
    @Test
    public void roverFacingAlwaysHaveToBeBetweenZeroAndThree(){
        Rover rover = new Rover(new Map(), 0, 0, 3);
        rover.input("l");
        Assertions.assertEquals(2, rover.getFacing());
        rover.input("ll");
        Assertions.assertEquals(0, rover.getFacing());
        rover.input("l");
        Assertions.assertEquals(3, rover.getFacing());
        rover.input("lllll");
        Assertions.assertEquals(2, rover.getFacing());

        Rover rover2 = new Rover(new Map(), 0, 0, 3);
        rover2.input("r");
        Assertions.assertEquals(0, rover2.getFacing());
        rover2.input("rr");
        Assertions.assertEquals(2, rover2.getFacing());
        rover2.input("r");
        Assertions.assertEquals(3, rover2.getFacing());
        rover2.input("rrrrr");
        Assertions.assertEquals(0, rover2.getFacing());
    }
    @Test
    public void roverMovementForwardNorth(){
        Rover rover = new Rover(new Map(),3,3,0);
        rover.input("f");
        Assertions.assertArrayEquals(new int[]{3, 4}, rover.position());
    }
    @Test
    public void roverMovementForwardEast(){
        Rover rover = new Rover(new Map(),3,3,1);
        rover.input("f");
        Assertions.assertArrayEquals(new int[]{4, 3}, rover.position());
    }
    @Test
    public void roverMovementForwardSouth(){
        Rover rover = new Rover(new Map(),3,3,2);
        rover.input("f");
        Assertions.assertArrayEquals(new int[]{3, 2}, rover.position());
    }
    @Test
    public void roverMovementForwardWest(){
        Rover rover = new Rover(new Map(),3,3,3);
        rover.input("f");
        Assertions.assertArrayEquals(new int[]{2, 3}, rover.position());
    }

    @Test
    public void roverMovementBackwardNorth(){
        Rover rover = new Rover(new Map(),3,3,0);
        rover.input("b");
        Assertions.assertArrayEquals(new int[]{3, 2}, rover.position());
    }
    @Test
    public void roverMovementBackwardEast(){
        Rover rover = new Rover(new Map(),3,3,1);
        rover.input("b");
        Assertions.assertArrayEquals(new int[]{2, 3}, rover.position());
    }
    @Test
    public void roverMovementBackwardSouth(){
        Rover rover = new Rover(new Map(),3,3,2);
        rover.input("b");
        Assertions.assertArrayEquals(new int[]{3, 4}, rover.position());
    }
    @Test
    public void roverMovementBackwardWest(){
        Rover rover = new Rover(new Map(),3,3,3);
        rover.input("b");
        Assertions.assertArrayEquals(new int[]{4, 3}, rover.position());
    }
    @Test
    public void roverShouldNotMoveIntoObstacles(){
        Map map = new Map();
        map.createCustomObstacle(3,3);
        Rover rover = new Rover(map, 2,3,1);
        Assertions.assertThrows(RuntimeException.class, () -> rover.input("f"));
    }
    @Test
    public void roverShouldWrapAroundToOppositeSideWhenExitingTheMap(){
        Rover rover = new Rover(new Map(1,1),0,0,0);
        rover.input("ffrffrffrffr");
        Assertions.assertArrayEquals(new int[]{0,0}, rover.position());
        Assertions.assertEquals(0,rover.getFacing());
    }
}
