package spartanpaint;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import processing.core.PApplet;

public class ColorChooserPanel
extends PAppletPanel<ColorChooserPanel.Applet>
{
    @Override
    protected Applet CreatePApplet()
    {
        return new Applet();
    }
    public class Applet extends PApplet
    {
        Dimension SizeBase = new Dimension(400, 360);
        Dimension SizeMultiple = new Dimension(1, 1);

        Point PositionSBPlane = new Point(0,0);
        Dimension SizeSBPlane = new Dimension(360, 360);

        Point PositionHueArea = new Point(360, 0);
        Dimension SizeHueArea = new Dimension(40, 360);

        @Override
        public void setup()
        {
            setMinimumSize(SizeBase);
            noLoop();
            addComponentListener(new ComponentListener()
            {
                @Override
                public void componentResized(ComponentEvent e) {
                    // Get the current size
                    Dimension current_size = getSize();
                    // Get the floored multiples of base size
                    int m_width = current_size.width / SizeBase.width;
                    int m_height = current_size.height / SizeBase.height;
                    // See if the multiple changed since the last redraw
                    if(SizeMultiple.width != m_width ||
                       SizeMultiple.height != m_height)
                    {
                        // Update the size multiple
                        SizeMultiple = new Dimension(m_height, m_height);
                        // Redraw
                        draw();
                    }
                }
                @Override
                public void componentMoved(ComponentEvent e) {
                }
                @Override
                public void componentShown(ComponentEvent e) {
                }
                @Override
                public void componentHidden(ComponentEvent e) {
                }
            });
        }

        @Override
        public void draw()
        {
            int sbplane_x = SizeMultiple.width * PositionHueArea.x;
            int sbplane_y = SizeMultiple.height * PositionHueArea.y;
            int sbplane_width = SizeMultiple.width * SizeSBPlane.width;
            int sbplane_height = SizeMultiple.height * SizeSBPlane.height;

            // Hue is recalled
            // Saturation spans the width
            // Brightness spans the height
            colorMode(HSB, 360, sbplane_width, sbplane_height);
            for(int j = 0; j < sbplane_height; j++)
            {
                for(int i = 0; i < sbplane_width; i++)
                {
                    stroke(0, i, j);
                    point(i, j);
                }
            }
        }
    }
}
