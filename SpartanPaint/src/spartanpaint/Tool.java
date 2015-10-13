/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartanpaint;

import javafx.scene.paint.Color;

/**
 * Generic class for tools, which are objects that allow the user to directly modify the image information by pinpointing coordinates and areas of effect.
 * @author Christian
 */
abstract public class Tool {
    protected String name;
    protected boolean active; //is the tool selected and activateable?
    
    protected int x;
    protected int y;
    
    protected Color SecondaryColor;
    protected Color primaryColor;
    
    protected Layer layer;

    
    public Tool(String name)
    {
        this.name = name;
    }
    
    //private CursorImage cursor; <--TODO--> cursor image funcitonality.
    
    /**
     * Applys the Tool's primary functionality, generally from a left mouse button.
     */
    abstract public void primaryApply(Coordinate origin);
    /**
     * Applys the Tool's secondary functionality, generally from a right click button.
     */
    abstract public void secondaryApply(Coordinate origin);
    
    
    public void toggleActive()
    {
        if (active)
        {
            deactivate();
        }
        else
        {
            activate();
        }
    } 
    public void activate()
    {
        active = true;
    }
    public void deactivate()
    {
        active = false;
    }
    
        /**
     * @return the layer
     */
    public Layer getLayer() {
        return layer;
    }

    /**
     * @param layer the layer to set
     */
    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    /**
     * @return the effect
     */


    /**
     * @return the SecondaryColor
     */
    public Color getSecondaryColor() {
        return SecondaryColor;
    }

    /**
     * @param SecondaryColor the SecondaryColor to set
     */
    public void setSecondaryColor(Color SecondaryColor) {
        this.SecondaryColor = SecondaryColor;
    }

    /**
     * @return the primaryColor
     */
    public Color getPrimaryColor() {
        return primaryColor;
    }

    /**
     * @param primaryColor the primaryColor to set
     */
    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }
    
}
