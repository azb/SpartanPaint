package spartanpaint;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * The layer contains a two-dimensional array of object Pixel. Layers can be
 * rendered in order.
 *
 * @author Christian
 */
public class Layer {
    //TEMPORARY -- FOR CanvasDemo -- DELETE ASAP.
    public int x1;
    public int x2;
    public int y1;
    public int y2;
    public int length;
    public int height;
            
            
    private ArrayList<ArrayList<Pixel>> x;
    
    
    String name;

    /**
     * Create a transparent layer.
     *
     * @param xSize the length of a layer (coordinate x)
     * @param ySize the height of a layer (coordinate y)
     */
    public Layer(int xSize, int ySize, String name) {
        x = new ArrayList<ArrayList<Pixel>>(xSize);
        this.name = name;
        this.height = xSize;
        this.length = ySize;
        for (int i = 0; i < xSize; i++) {
            x.set(i, new ArrayList<Pixel>());
        }
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                x.get(i).set(j, new Pixel());
            }

        }
    }
    /**
     * Create a layer of color.
     *
     * @param xSize the length of a layer (coordinate x)
     * @param ySize the height of a layer (coordinate y)
     * @param color
     */
    public Layer(int xSize, int ySize, Color color, String name) {
        x = new ArrayList<ArrayList<Pixel>>(xSize);
        this.name = name;
        this.height = xSize;
        this.length = ySize;

        for (int i = 0; i < xSize; i++) {
            x.set(i, new ArrayList<Pixel>());
        }
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                x.get(i).set(j, new Pixel(color));
            }
        }

    }
    /**
     * Returns the pixel in a specified coordinate within the layer.
     * @param c the coordinate of the pixel
     * @return  the pixel
     */
    public Pixel getPixel(Coordinate c) {
        if (x.get(c.getX()).get(c.getY()) != null) {
            return x.get(c.getX()).get(c.getY());
        } else {
            return null;
        }
    }
    /**
     * Returns the pixel in a specified x/y value within the layer.
     * @param xCoordinate the coordinate of the pixel
     * @param yCoordinate the coordinate of the pixel
     * @return  the pixel
     */
    public Pixel getPixel(int xCoordinate, int yCoordinate) {
        if (x.get(xCoordinate).get(yCoordinate) != null) {
            return x.get(xCoordinate).get(yCoordinate);
        } else {
            return null;
        }
    }
    /**
     * Replaces a valid pixel at coordinate (x,y) with a specified pixel
     * @param xCoordinate the coordinate of the pixel pixel to replace.
     * @param yCoordinate the coordinate of the pixel pixel to replace.
     * @param pixel the new pixel
     */
    public void setPixel(int xCoordinate, int yCoordinate, Pixel pixel) {
        if (x.get(xCoordinate).get(yCoordinate) != null) {
            x.get(xCoordinate).set(yCoordinate, pixel);
        }
    }
    /**
     * Replaces a valid pixel at a specified Coordinate with a specified pixel
     * @param c the coordinate of the pixel to replace.
     * @param pixel the new pixel
     */
    public void setPixel(Coordinate c, Pixel pixel) {
        if (x.get(c.getX()).set(c.getY(), pixel) != null) {
            x.get(c.getX()).set(c.getY(), pixel);
        }
    }
}
