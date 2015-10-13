
package spartanpaint;
import java.util.ArrayList;
/**
 * The BrushShape class is an abstract class which provides guidelines on creating different kinds of brushes.
 * @author Christian
 */
public abstract class BrushShape {
    
    final private int DEFAULT_SIZE = 10;
    
    protected String name;
    protected int size; // the diameter size of the brush in pixels from x/y
    //Layer layer;
    public BrushShape(int size, String name)
    {
        this.name = name;
        this.size = size;
    }
    public BrushShape(String name)
    {
        this.name = name;
        this.size = DEFAULT_SIZE;
    }
    /*
    Layer should be a determined more higher up.
    public void setLayer(Layer layer)
    {
        this.layer = layer;
    }
    public Layer getLayer()
    {
        return this.layer;
    }
    */
    public void setSize(int size)
    {
        this.size = size;
    }
    public int getSize()
    {
        return this.size;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    
    /**
     * The findPixels method is used to return a list of coordinates of potentially valid Pixels that intersect the BrushShape.
     * It is implemented in children BrushTools.
     * @param x The x coordinate of the mouse
     * @param y The y coordinate of the mouse
     * @return The list of pixels that intersect the BrushShape.
     */
    abstract public ArrayList<Coordinate>findCoordinates(int x, int y);
}
