/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spartanpaint;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * Acts as a template for Effects, which contains an algorithm that takes in initial Pixel color values and creates new ones
 * @author Christian
 */
public abstract class Effect {
    
    protected String name;
    
    public Effect(String name)
    {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
/**
 * Initiates an algorithm to modify the Color value of Pixels.
 * @param pixels the list of Pixels that need processing.
 * @return a list of Pixels that have been processed.
 */
    abstract public ArrayList<Pixel> calculatePrimaryEffect(ArrayList<Pixel> pixels, Color primaryColor, Color secondaryColor);
    abstract public ArrayList<Pixel> calculateSecondaryEffect(ArrayList<Pixel> pixels, Color primaryColor, Color secondaryColor);
}
