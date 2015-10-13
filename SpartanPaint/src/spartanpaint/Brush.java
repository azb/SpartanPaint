/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartanpaint;
import javafx.scene.paint.Color;
import java.util.ArrayList;
/**
 *  Applys a BrushEffect to any Pixels on the Canvas Layer that intersect its BrushShape.
 * @author Christian
 */
public class Brush extends Tool {
    private BrushShape shape;
    private Effect effect; 
    

    public Brush(Layer layer, BrushShape shape, Effect effect, String name)
    {
        super(name);
        this.layer = layer;
        this.shape = shape;
        this.effect = effect;
    }

    /**
     * Debugging method- initializes the brush with the SquareBrush BrushShape and the PaintEffect Effect.
     */
    public void initializeExampleBrush()
    {
        setShape(new SquareBrush());
        setEffect(new PaintEffect());
    }
    
     /**
     * Applys the primary brush's effects in the shape of the BrushShape on the Layer.
     * 
     */
    @Override
    public void primaryApply(Coordinate origin)
    {
        if (shape==null)
        {
            throw new BrushHasNoValidShapeException(this);
        }
        //Initialize our validPixels list.
        //a valid pixel is a pixel that exists in a layer
        ArrayList<Pixel> validPixels = new ArrayList<Pixel>(shape.getSize()*shape.getSize()); //The size of the brush squared is the maximum amount of pixels that can be modified.
        
        //Initialize our validCoordinates list.
        //a valid coordinate is a coordinate of a pixel that exists in a layer.
        ArrayList<Coordinate> validCoordinates = new ArrayList<Coordinate>(shape.getSize()*shape.getSize());
        
        
        //use the BrushShape method to create a list of potential coordinates.
        //a potential coordinate is a coordinate that is ossibly valid.
        //a case where a pixel is invalid is when it is out of bounds of the canvas.
        ArrayList<Coordinate> potentialPixels = shape.findCoordinates(x, y);
        
        
        //retrieve list of valid pixels.
        for (Coordinate coordinate: potentialPixels) {
            
            //retrieve each Pixel from the Layer.
            //the layer will return null if the pixel does not exist.
            Pixel potentialPixel = layer.getPixel(coordinate);
            
            //check if the pixel is valid
            if(potentialPixel != null)
            {
                validPixels.add(potentialPixel);
                validCoordinates.add(coordinate);
            }
        }
        
        //We no longer need this list
        potentialPixels = null;
        
        //the Effect class will calculate a new list of modified pixels given a list of valid pixels.
        ArrayList<Pixel> modifiedPixels = effect.calculatePrimaryEffect(validPixels, primaryColor, SecondaryColor);
        
        
        //iterate through the list of coordinates for valid Pixels.
        //replace each old Pixel with the modified Pixel.
        for (int i = 0; i < validCoordinates.size(); i++)
        {
            //the setPixel method already checks to see if the coordinate is valid before replacing the pixel.
            layer.setPixel(validCoordinates.get(i), modifiedPixels.get(i));
        }
    }
    
    /**
     * Applys the secondary brush's effects in the shape of the BrushShape on the Layer.
     * 
     */
    @Override
    public void secondaryApply(Coordinate origin)
    {
        ArrayList<Pixel> validPixels = new ArrayList<Pixel>(shape.getSize()*shape.getSize());
        ArrayList<Coordinate> validCoordinates = new ArrayList<Coordinate>(shape.getSize()*shape.getSize());
        
        ArrayList<Coordinate> potentialPixels = shape.findCoordinates(x, y);
        
        for (Coordinate coordinate: potentialPixels) {
            Pixel potentialPixel = layer.getPixel(coordinate);
            if(potentialPixel != null)
            {
                validPixels.add(potentialPixel);
                validCoordinates.add(coordinate);
            }
        }
        
        potentialPixels = null;
        
        ArrayList<Pixel> modifiedPixels = effect.calculateSecondaryEffect(validPixels, primaryColor, SecondaryColor);
        
        for (int i = 0; i < validCoordinates.size(); i++)
        {
            layer.setPixel(validCoordinates.get(i), modifiedPixels.get(i));
        }
    }
    
    
    /*
    
    ENCAPSULATION METHODS.
    
    */
    
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * @return the shape
     */
    public BrushShape getShape() {
        return shape;
    }
    /**
     * @param shape the shape to set
     */
    public void setShape(BrushShape shape) {
        this.shape = shape;
    }
        public Effect getEffect() {
        return effect;
    }
    /**
     * @param effect the effect to set
     */
    public void setEffect(Effect effect) {
        this.effect = effect;
    }


}
