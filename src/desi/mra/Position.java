package desi.mra;

public class Position {

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int get_x() {
        return x;
    }
    public int get_y() {
        return y;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(x+""+y);
    }

    @Override
    public boolean equals(Object obj) {
        Position other = (Position) obj;
        return other.get_x() == this.x && other.get_y() == this.y;
    }

    private int x,y;
}
