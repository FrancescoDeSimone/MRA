package desi.mra;

import java.util.*;

public class Rover {

    public Rover(World world) throws RoverException {
        if(world == null) throw new RoverException("World can't be null");
        this.world = world;
    }

    private Position changePosition(Rotation r, int direction){
        switch (r){
            case NORD:
                return new Position(this.position.get_x(),Math.floorMod(this.position.get_y()+(1*direction),world.get_edge()));
            case EAST:
                return new Position(Math.floorMod(this.position.get_x()+(1*direction),world.get_edge()),this.position.get_y());
            case WEST:
                return new Position(Math.floorMod(this.position.get_x()+(-1*direction),world.get_edge()),this.position.get_y());
            case SOUTH:
                return new Position(this.position.get_x(),Math.floorMod(this.position.get_y()+(-1*direction),world.get_edge()));
            default:
                return null;
        }
    }

    public String move(String movements) throws RoverException {
        if(movements == null || !movements.matches("^[fblr]*$"))
            throw new RoverException("invalid movement");
        LinkedHashSet<String> position_obstacle = new LinkedHashSet<>();
        for(char c : movements.toCharArray()){
            Position new_position = position;
            switch (c){
                case 'f': new_position = changePosition(rotation,FORWARD); break;
                case 'b': new_position = changePosition(rotation,BACKWARD); break;
                case 'l': rotation = rotation.left();  break;
                case 'r': rotation = rotation.right(); break;
            }
            if(world.get_obstacles().contains(new_position))
                position_obstacle.add("("+new_position.get_x()+","+new_position.get_y()+")");
            else
                position = new_position;
        }
        return "("+position.get_x()+","+position.get_y()+","+rotation.toString()+")"
                + position_obstacle.toString().substring(1,position_obstacle.toString().length()-1)
                .replaceAll("\\),\\s",")");
    }
    private World world;
    private Position position = new Position(0,0);
    private Rotation rotation = Rotation.NORD;
    private final int FORWARD = 1, BACKWARD = -1;
    enum Rotation{NORD,EAST,SOUTH,WEST;
        public Rotation right() {
            return values()[Math.floorMod(ordinal() + 1,values().length)];
        }
        public Rotation left() {
            return values()[Math.floorMod(ordinal() - 1,values().length)];
        }
    }
}