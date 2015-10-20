/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartanpaint;
import java.util.ArrayList;

import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;

/**
 * Renders the Layer information into a single image.
 * @author Christian
 */
public class LayerRenderer {
    private final LayerContainer container;
    public LayerRenderer( LayerContainer c)
    {
        container = c;
    }
    /**
     * Returns an image rendered from all information contained in the LayerRender.
     * Blends the color of all the enabled layers from bottom to top.
     * @return the rendered image
     */
    public WritableImage render()
    {
        //Create a image to return and to put colors into pixel by pixel.
        WritableImage image = new WritableImage(container.getLayer(0).getLength(), container.getLayer(0).getHeight());
        
        //Create a temporary Layer to aggregate and blend all the colors from the layers into.
        Layer l = new Layer(container.getLayer(0).getLength(), container.getLayer(0).getHeight(), "Temporary Layer");
        
        
        //For each layer in the container starting from the bottom most layer.
        for(int i = (container.size()-1); i>0; i++)
        {
            //If the layer is enabled, render it.
            if(container.getLayer(i).isEnabled())
            {
                //for each pixel, starting from the top left of the image...
                //...descending from left to right, top to down to the bottom right.
                for(int j=0;j<l.getLength();j++)
                {
                    for(int k=0;k<l.getHeight();k++)
                    {
                        //blend the Pixels of the :ayer at this coordinate together
                        //and save it to the temporary Layer.
                        l.setPixel(j, k, l.getPixel(j,k).overBlend(container.getLayer(i).getPixel(j, k)));
                    }
                }
            }
        }
        //Finally, for each pixel, starting from the top left of the image...
        //...descending from left to right, top to down to the bottom right. 
        for(int i = 0;i <l.getLength(); i++)
        {
            for(int j = 0;j <l.getHeight(); j++)
            {
                //set the color of each coordinate of the image...
                //... to the color value of the completely calculated Pixel.
                image.getPixelWriter().setColor(i , j, l.getPixel(i, j).getColor());
            }
        }
        
        //Return the image with the colors set exactly to the composited layers.
        return image;
    }
    /**
     * Returns an image render information contained in the LayerRender that are relevant.
     * A pixel is relevant if it has been changed since the last call to render.
     * Blends the color of all the enabled layers from bottom to top.
     * @param  oldImage The old image to render new information over. Typically found in the Canvas.
     * @param  coordinates the list of coordinates of relevant pixels.
     * @return the rendered image
     */
    public Image selectiveRender(WritableImage oldImage, ArrayList<Coordinate> coordinates)
    {
        //We use old image, but I feel like we should create a brand new image
        //for the sake of encapsulation..?
        
        //Create a temporary Layer to aggregate and blend all the colors from the layers into.
        Layer l = new Layer(container.getLayer(0).getLength(), container.getLayer(0).getHeight(), "Temporary Layer");
        //We can save memory by creating an ArrayList of pixels instead of generating a whole new layer.
        //It's not a priority, but it's an optimization we can do later
        //down the dev cycle.
        
        //For each layer in the container starting from the bottom most layer.
        for(int i = (container.size()-1); i>0; i++)
        {
            //If the layer is enabled, render it.
            if(container.getLayer(i).isEnabled())
            {
                //for each coordinate of a recently modified Pixel
                for(Coordinate c: coordinates)
                {

                    l.setPixel(c.getX(), c.getY(), l.getPixel(c.getX(),c.getY()).overBlend(container.getLayer(i).getPixel(c.getX(), c.getY())));
                }
            }
        }
        //Finally, for each coordinate...
        for(Coordinate c: coordinates)
        {
            //replace the color from the old image to the new color.
            oldImage.getPixelWriter().setColor(c.getX() , c.getY(), l.getPixel(c.getX(), c.getY()).getColor());
        }
        
        
        //Return the image with the colors set exactly to the composited layers.
        return oldImage;
    }
            
        
        
        
        
    
}
