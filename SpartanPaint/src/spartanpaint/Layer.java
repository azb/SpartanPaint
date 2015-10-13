package spartanpaint;

import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * The layer contains a two-dimensional array of object Color. Layers can be
 * rendered in order.
 *
 * @author Christian
 */
public class Layer {

    private ArrayList<ArrayList<Pixel>> x;
    Sring name;

    /**
     * Create a transparent layer.
     *
     * @param xSize the length of a layer (coordinate x)
     * @param ySize the height of a layer (coordinate y)
     */
    public Layer(int xSize, int ySize, String name) {
       x = new ArrayList<ArrayList<Pixel>>(xSize);
        this.name = name;

        for (int i = 0; i < xSize; i++) {
            x.set(i, new ArrayList<Pixel>());
        }
        for (int i = 0; i <xSize; i++)
        {
            for (int j = 0; j < ySize; j++)
            {
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

        for (int i = 0; i < xSize; i++) {
            x.set(i, new ArrayList<Pixel>());
        }
        for (int i = 0; i <xSize; i++)
        {
            for (int j = 0; j < ySize; j++)
            {
                x.get(i).set(j, new Pixel(color));
            }
        }

    }
}
