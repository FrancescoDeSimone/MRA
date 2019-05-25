package desi.mra;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class RoverTest {

    @Test
    public void test_landing_rover() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        World w = new World(10,obstacles);
        Rover r = new Rover(w);
        String movement = r.move("");
        assertEquals("(0,0,NORD)",movement);
    }

    @Test(expected = RoverException.class)
    public void test_create_invalid_rover_world_null() throws WordException, RoverException {
        new Rover(null);
    }

    @Test
    public void test_turning_rover_east() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        World w = new World(10,obstacles);
        Rover r = new Rover(w);
        String movement = r.move("r");
        assertEquals("(0,0,EAST)",movement);
    }

    @Test
    public void test_turning_rover_weast() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        World w = new World(10,obstacles);
        Rover r = new Rover(w);
        String movement = r.move("l");
        assertEquals("(0,0,WEST)",movement);
    }

    @Test
    public void test_moving_rover_forward() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        World w = new World(10,obstacles);
        Rover r = new Rover(w);
        r.move("ffffffrfffffffl");
        String movement = r.move("f");
        assertEquals("(7,7,NORD)",movement);
    }

    @Test
    public void test_moving_rover_backward() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        World w = new World(10,obstacles);
        Rover r = new Rover(w);
        r.move("ffffffffrfffff");
        String movement = r.move("b");
        assertEquals("(4,8,EAST)",movement);
    }

    @Test
    public void test_moving_rover_combine() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        World w = new World(10,obstacles);
        Rover r = new Rover(w);
        String movement = r.move("ffrff");
        assertEquals("(2,2,EAST)",movement);
    }

    @Test
    public void test_moving_rover_wrapping() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        World w = new World(100,obstacles);
        Rover r = new Rover(w);
        String movement = r.move("b");
        assertEquals("(0,99,NORD)",movement);
    }

    @Test
    public void test_moving_rover_wrapping_multiple_time_forward_in_y_axes() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        World w = new World(10,obstacles);
        Rover r = new Rover(w);
        String movement = r.move(new String(new char[101]).replace('\0', 'f'));
        assertEquals("(0,1,NORD)",movement);
    }

    @Test
    public void test_moving_rover_wrapping_multiple_time_backward_in_y_axes() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        World w = new World(10,obstacles);
        Rover r = new Rover(w);
        String movement = r.move(new String(new char[10001]).replace('\0', 'b'));
        assertEquals("(0,9,NORD)",movement);
    }

    @Test
    public void test_moving_rover_wrapping_multiple_time_forward_in_x_axes() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        World w = new World(10,obstacles);
        Rover r = new Rover(w);
        String movement = r.move("r" + new String(new char[12345]).replace('\0', 'f'));
        assertEquals("(5,0,EAST)",movement);
    }

    @Test
    public void test_moving_rover_wrapping_multiple_time_backward_in_x_axes() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        World w = new World(10,obstacles);
        Rover r = new Rover(w);
        String movement = r.move("r" + new String(new char[6789]).replace('\0', 'b'));
        assertEquals("(1,0,EAST)",movement);
    }

    @Test
    public void test_moving_rover_single_obstacle() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(2,2));
        World w = new World(100,obstacles);
        Rover r = new Rover(w);
        String movement = r.move("ffrfff");
        assertEquals("(1,2,EAST)(2,2)",movement);
    }

    @Test
    public void test_moving_rover_multiple_obstacle() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(2,1));
        obstacles.add(new Position(2,2));
        World w = new World(100,obstacles);
        Rover r = new Rover(w);
        String movement = r.move("ffrfffrflf");
        assertEquals("(1,1,EAST)(2,2)(2,1)",movement);
    }

    @Test
    public void test_moving_rover_multiple_obstacle2() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(0,1));
        obstacles.add(new Position(0,3));
        World w = new World(100,obstacles);
        Rover r = new Rover(w);
        String movement = r.move("ffffrflffflfffflfffrflll");
        assertEquals("(0,0,NORD)(0,1)(0,3)",movement);
    }

    @Test
    public void test_moving_rover_wrapping_obstacle() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(0,9));
        World w = new World(10,obstacles);
        Rover r = new Rover(w);
        String movement = r.move("b");
        assertEquals("(0,0,NORD)(0,9)",movement);
    }

    @Test
    public void test_moving_rover_tour_around_planet() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(2,2));
        obstacles.add(new Position(0,5));
        obstacles.add(new Position(5,0));
        World w = new World(6,obstacles);
        Rover r = new Rover(w);
        String movement = r.move("ffrfffrbbblllfrfrbbl");
        assertEquals("(0,0,NORD)(2,2)(0,5)(5,0)",movement);
    }

    @Test(expected = RoverException.class)
    public void test_invalid_movement() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(2,2));
        obstacles.add(new Position(0,5));
        obstacles.add(new Position(5,0));
        World w = new World(6,obstacles);
        Rover r = new Rover(w);
        r.move("not a valid one");
    }

    @Test(expected = RoverException.class)
    public void test_invalid_movement_2() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(2,2));
        obstacles.add(new Position(0,5));
        obstacles.add(new Position(5,0));
        World w = new World(6,obstacles);
        Rover r = new Rover(w);
        r.move("fblol");
    }

    @Test(expected = RoverException.class)
    public void test_invalid_movement_null() throws WordException, RoverException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(2,2));
        obstacles.add(new Position(0,5));
        obstacles.add(new Position(5,0));
        World w = new World(6,obstacles);
        Rover r = new Rover(w);
        r.move(null);
    }
}
