/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartanpaint;
import java.util.ArrayList;

/**
 * A simple square brush
 * @author Christian
 */

public class SquareBrush extends BrushShape {
    /**
     * Creates a square brush with a specified diameter default parameters: name: "Square Brush"
     * @param size the diameter of the brush in pixels.
     */
    public SquareBrush(int size)
    {
        super(size, "Square Brush");
    }
    /**
     * Creates a square brush with default parameters: size: 10, name: "Square Brush"
     */
    public SquareBrush()
    {
        super(10, "Square Brush");
    }
    /**
     * finds coordinates of potential pixels to be modified.
     * @param x the x coordinate of the cursor
     * @param y the y coordinate of the cursor
     * @param layer the layer to find potential pixels in.
     * @return a list of potential coordinates of Pixels intersecting the brush.
     */
    @Override
    public ArrayList<Coordinate>findCoordinates(int x, int y)
    {
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>(size*size);
        
        int upperbound = y-(size/2);
        int lowerbound = y+(size/2);
        int leftbound = x-(size/2);
        int rightbound = x+(size/2);
        
        for(int i = upperbound; i <= lowerbound; i++)
        {
            for(int j = leftbound; j<= rightbound; j++)
            {
                coordinates.add(new Coordinate(j,i));
            }
        }
        return coordinates;
    }
    
    
}
