package canvas;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import processing.core.*;

public class CanvasApplet extends PApplet
{
    private boolean Initialized = false;
    public CanvasApplet()
    {
        super();
        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentShown(ComponentEvent e)
            {
                // Late initialization so that NetBeans doesn't scream
                if(!Initialized)
                {
                    init();
                    Initialized = true;
                }
            }
        });
        // Small hack to force an componentShown event
        setVisible(false);
        setVisible(true);
    }
    
    @Override
    public void setup()
    {
        size(800,600);
        background(0);
    }
    
    @Override
    public void draw()
    {
        stroke(255);
        if (mousePressed)
            line(mouseX,mouseY,pmouseX,pmouseY);
    }

    
}
