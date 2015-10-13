package spartanpaint;

/**
 * This class encapsulates a 2d coordinate.
 * @author Christian
 */
public class Coordinate {
    private int x;
    private int y;
    
    public Coordinate(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
    /**
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }
    /**
     * @param x the x coordinate to set
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }
    /**
     * @param y the y coordinate to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Calculates the distance from a specified coordinate to this coordinate using the Pythagoras's Distance Formula.
     * @param c the Coordinate to find the distance to.
     * @return the distance between Coordinate c and this coordinate in pixels.
     */
    public int distance(Coordinate c)
    {
        return (int) Math.sqrt( (Math.pow( (double)(this.x-c.getX() ) ,2) + (Math.pow( (double)(this.y-c.getY()) ,2) ) ));
    }
    
}
