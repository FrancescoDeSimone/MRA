package desi.mra;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class WorldTest {

    @Test
    public void test_world_create_edge_check() throws WordException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(9,4));
        obstacles.add(new Position(7,4));
        obstacles.add(new Position(9,3));
        obstacles.add(new Position(5,4));
        obstacles.add(new Position(2,8));
        obstacles.add(new Position(4,5));
        obstacles.add(new Position(1,1));
        obstacles.add(new Position(0,6));
        obstacles.add(new Position(0,5));
        obstacles.add(new Position(1,1));
        World w = new World(10,obstacles);
        int edge_height = w.get_edge();
        assertEquals(10,edge_height);
    }

    @Test
    public void test_world_create_obstacle_check() throws WordException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(2,2));
        World w = new World(10,obstacles);
        HashSet<Position> obst = w.get_obstacles();
        assertTrue(obst.contains(new Position(2,2)));
    }

    @Test(expected = WordException.class)
    public void test_invalid_world_create_too_many_obstacles() throws WordException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(0,0));
        obstacles.add(new Position(0,1));
        obstacles.add(new Position(0,2));
        obstacles.add(new Position(1,0));
        obstacles.add(new Position(1,1));
        obstacles.add(new Position(1,2));
        obstacles.add(new Position(2,0));
        obstacles.add(new Position(2,1));
        obstacles.add(new Position(2,2));
        new World(3,obstacles);
    }


    @Test(expected = WordException.class)
    public void test_invalid_world_create_too_many_obstacles2() throws WordException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(0,0));
        obstacles.add(new Position(0,1));
        obstacles.add(new Position(0,2));
        obstacles.add(new Position(0,3));
        obstacles.add(new Position(1,0));
        obstacles.add(new Position(1,1));
        obstacles.add(new Position(1,2));
        obstacles.add(new Position(1,3));
        obstacles.add(new Position(2,0));
        new World(3,obstacles);
    }

    @Test(expected = WordException.class)
    public void test_invalid_world_create_obstacle_out_of_bound() throws WordException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(10,4));
        obstacles.add(new Position(7,4));
        obstacles.add(new Position(10,3));
        obstacles.add(new Position(5,4));
        obstacles.add(new Position(2,8));
        obstacles.add(new Position(4,5));
        obstacles.add(new Position(1,1));
        obstacles.add(new Position(0,6));
        obstacles.add(new Position(0,5));
        obstacles.add(new Position(1,1));
        obstacles.add(new Position(2,0));
        new World(10,obstacles);
    }

    @Test(expected = WordException.class)
    public void test_invalid_world_create_obstacle_out_of_bound2() throws WordException {
        HashSet<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(3,4));
        obstacles.add(new Position(7,4));
        obstacles.add(new Position(3,3));
        obstacles.add(new Position(5,10));
        obstacles.add(new Position(2,8));
        obstacles.add(new Position(4,5));
        obstacles.add(new Position(1,1));
        obstacles.add(new Position(0,6));
        obstacles.add(new Position(0,5));
        obstacles.add(new Position(1,1));
        new World(10,obstacles);
    }

    @Test(expected = WordException.class)
    public void test_invalid_world_null_obstacle() throws WordException {
        new World(10,null);
    }
}
