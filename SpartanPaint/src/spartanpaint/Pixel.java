package spartanpaint;
import javafx.scene.paint.Color;

/**
 * A Pixel represents a color value located saved onto a layer.
 * A pixel's x/y should be determined an a two-dimensional array contained by a Layer class.
 * This class can be subsituted for the Color Class, but this is a place holder just in case we need more functionality.
 * @author Christian
 */
public class Pixel {
    private Color color;
    public Pixel()
    {
        color = new Color(0, 0, 0, 0);
    }
    public Pixel(double red, double green, double blue, double opacity)
    {
        color = new Color(red, green, blue, opacity);
    }
        public Pixel(Color color)
    {
        this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getOpacity());
    }
    public Color getColor()
    {
        return color;
    }
}
