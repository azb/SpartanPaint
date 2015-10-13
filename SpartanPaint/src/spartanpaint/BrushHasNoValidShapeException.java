/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartanpaint;

/**
 *  This exception occurs when a Brush attempts to find pixels without a valid brush shape.
 * @author Christian
 */
public class BrushHasNoValidShapeException extends RuntimeException{
    
    final private static String DEFAULT_MESSAGE = "The active brush does not have a valid shape!";
    
    public BrushHasNoValidShapeException(BrushShape brush)
    {
        super(DEFAULT_MESSAGE + " BrushShape object info: " + brush.toString());
    }
    public BrushHasNoValidShapeException(String message)
    {
        super(message);
    }
    public BrushHasNoValidShapeException()
    {
        super(DEFAULT_MESSAGE);
    }
}
