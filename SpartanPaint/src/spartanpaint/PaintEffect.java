/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartanpaint;
import java.util.ArrayList;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Changes the Colors of the Pixels into one uniform color.
 * @author Christian
 */
public class PaintEffect extends Effect{
    
    /**
     * Creates example effect Paint, which sets the color of Pixels within a BrushSpace to a specified color.
     */
    public PaintEffect()
    {
        super("Paint");
    }
    /**
     * Sets the color of Pixels within a BrushSpace to the primary color.
     * @param pixels the list of pixels to modify
     * @param primaryColor 
     * @param secondaryColor
     * @return a list of modified pixels. These pixels must be applied to a layer and drawn before any effect is seen.
     */
    @Override
    public ArrayList<Pixel> calculatePrimaryEffect(ArrayList<Pixel> pixels, Color primaryColor, Color secondaryColor)
    {
        ArrayList<Pixel> modifiedPixels = new ArrayList<Pixel>(pixels.size());
        for(Pixel p : pixels)
        {
            modifiedPixels.add(new Pixel(primaryColor));
        }
        return modifiedPixels;
    }
    /**
     * Sets the color of Pixels within a BrushSpace to the secondary color.
     * @param pixels the list of pixels to modify
     * @param primaryColor 
     * @param secondaryColor
     * @return a list of modified pixels. These pixels must be applied to a layer and drawn before any effect is seen.
     */
    @Override
    public ArrayList<Pixel> calculateSecondaryEffect(ArrayList<Pixel> pixels, Color primaryColor, Color secondaryColor)
    {
         ArrayList<Pixel> modifiedPixels = new ArrayList<Pixel>(pixels.size());
        for(Pixel p : pixels)
        {
            modifiedPixels.add(new Pixel(secondaryColor));
        }
        return modifiedPixels;
    }
}
