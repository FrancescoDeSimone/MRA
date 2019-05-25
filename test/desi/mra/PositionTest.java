package desi.mra;

import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void test_get_hash_code(){
        Position p = new Position(10,5);
        int hashcode = p.hashCode();
        assertEquals(105,hashcode);
    }

    @Test
    public void test_get_hash_code2(){
        Position p = new Position(5,10);
        int hashcode = p.hashCode();
        assertEquals(510,hashcode);
    }

    @Test
    public void test_equals(){
        Position p = new Position(5,10);
        Position p1 = new Position(5,10);
        assertEquals(p1,p);
    }

    @Test
    public void test_not_equals(){
        Position p = new Position(5,10);
        Position p1 = new Position(4,10);
        assertNotEquals(p1,p);
    }
}
