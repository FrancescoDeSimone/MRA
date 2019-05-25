package desi.mra;
import java.util.HashSet;

public class World {

    public World(int edge, HashSet<Position> obstacles) throws WordException {
        if(obstacles == null)
            throw new WordException("Obstacles can't be null");

        if(obstacles.size() >= (edge*edge))
            throw new WordException("Too many obstacles");

        this.edge = edge;
        for(Position obstacle:obstacles)
            if(obstacle.get_x() >= edge || obstacle.get_y() >= edge)
                throw new WordException("Obstacle "+ obstacle.get_x()+"-"+obstacle.get_y() +" out of bound");

        this.obstacles = obstacles;
    }

    public int get_edge() {
        return edge;
    }

    public HashSet<Position> get_obstacles() {
        return obstacles;
    }
    private int edge;
    private HashSet<Position> obstacles;
}
