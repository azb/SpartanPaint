/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartanpaint;
import javafx.scene.paint.Color;
/**
 *  Contains the effect to apply to the image.
 * @author Christian
 */
public class Brush {
    int x;
    int y;
    
    BrushShape shape;
    Layer layer;
    Effect effect;
    Color SecondaryColor;
    Color primaryColor;
    
    public Brush(Layer layer, BrushShape shape, Effect effect)
    {
        this.layer = layer;
        this.shape = shape;
        this.effect = effect;
    }
    /**
     * Applys the brush's effects in the shape of the BrushShape on the Layer.
     * 
     */
    public void apply()
    {
        
    }
    
    public void initializeExampleBrush()
    {
    }
}
