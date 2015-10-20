package spartanpaint;
import javafx.scene.paint.Color;

/**
 * Encapsulates a 24-bit sRGB value. Used instead of Javafx Color class because the precise memory size of Color is unknown.
 * Specifically used to store color information in the canvas. Upon rendering, class Color is used by javaFX to render the image.
 * 
 * @author Christian
 */
public class Pixel {
    private byte red;
    private byte green;
    private byte blue;
    private byte opacity;
    
    /**
     * Creates a Pixel of value 0 in all channels.
     */
    public Pixel()
    {
        red=0; green=0; blue =0; opacity=0;
    }
    /**
     * Creates a Pixel of a specified value.
     * @param red the value of the red channel
     * @param green the value of the green channel
     * @param blue the value of the blue channel
     * @param opacity the value of the alpha channel
     */
    public Pixel(int red, int green, int blue, int opacity)
    {
        this.red = intToByte(red);
        this.green = intToByte(green);
        this.blue = intToByte(blue);
        this.opacity = intToByte(opacity);
    }
    /**
     * Creates a Pixel using a specified JavaFX Color
     * @param color the JavaFX color object
     */
        public Pixel(Color color)
    {
        this.red = intToByte((int)(color.getRed()*255));
        this.green = intToByte((int)(color.getGreen()*255));
        this.blue = intToByte((int)(color.getBlue()*255));
        this.opacity = intToByte((int)(color.getOpacity()*255));
    }
        /**
         * Gets the JavaFX Color based on the data stored by Pixel.
         * @return the Color
         */
    public Color getColor()
    {
        return new Color(getRed(), getGreen(), getBlue(), getOpacity());
    }
    /**
     * Creates a pixel that is the alpha composite of a specified Pixel over this Pixel.
     * The formula is directly derived from Wikipedia:Alpha_compositing: https://en.wikipedia.org/wiki/Alpha_compositing
     * @param p the Pixel that is ON TOP.
     * @return the composited Pixel.
     */
    public Pixel overBlend(Pixel p)
    {
        int newRed = (p.getRed()*p.getOpacity())+(byteToInt(red)*byteToInt(opacity)*(255-byteToInt(opacity)));
        int newBlue = (p.getBlue()*p.getOpacity())+(byteToInt(blue)*byteToInt(opacity)*(255-byteToInt(opacity)));
        int newGreen = (p.getGreen()*p.getOpacity())+(byteToInt(green)*byteToInt(opacity)*(255-byteToInt(opacity)));
        int newOpacity = p.getOpacity()+byteToInt(opacity)*(1-p.getOpacity());
        return new Pixel(newRed,newBlue,newGreen,newOpacity);
    }
    
    
    /**
     * Java does not support unsigned bytes, so this method interprets an int of range 0-255 to as an byte of range -128 to 127.
     * @param b byte to convert
     * @return int of range 0-255
     */
    static private int byteToInt(byte b)
    {
        return b+128;
    }
    /**
     * Java does not support unsigned bytes, so this method interprets a byte of range -128 to 127  to as an int of range 0 - 255.
     * @param b byte to convert
     * @return byte of range -128 to 127
     */
    static private byte intToByte(int i)
    {
        return (byte)(i-128);
    }

    /**
     * @return the red
     */
    public int getRed() {
        return byteToInt(red);
    }

    /**
     * @param red the red to set
     */
    public void setRed(int red) {
        this.red = intToByte(red);
    }

    /**
     * @return the green
     */
    public int getGreen() {
        return byteToInt(green);
    }

    /**
     * @param green the green to set
     */
    public void setGreen(int green) {
        this.green = intToByte(green);
    }

    /**
     * @return the blue
     */
    public int getBlue() {
        return byteToInt(blue);
    }

    /**
     * @param blue the blue to set
     */
    public void setBlue(int blue) {
        this.blue = intToByte(blue);
    }

    /**
     * @return the opacity
     */
    public int getOpacity() {
        return byteToInt(opacity);
    }

    /**
     * @param opacity the opacity to set
     */
    public void setOpacity(byte opacity) {
        this.opacity = intToByte(opacity);
    }
}
