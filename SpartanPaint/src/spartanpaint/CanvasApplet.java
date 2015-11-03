package spartanpaint;

import processing.core.*;

public class CanvasApplet extends PApplet
{
    int ppmouseX;
    int ppmouseY;
    
    public int SomeColor;
    
    @Override
    public void setup()
    {
        //frameRate(24);
        size(200, 200);
        background(0);
        ppmouseX = mouseX;
        ppmouseY = mouseY;
        SomeColor = color(255, 0, 0);
    }
    @Override
    public void draw()
    {
        fill(0, 2); // * (frameCount % 2)
        //rect(0,0,width,height);
        stroke(255);
        strokeWeight(2);
        
        float midx = 0.5f * (pmouseX + mouseX);
        float midy = 0.5f * (pmouseY + mouseY);
        
        float pmidx = 0.5f * (ppmouseX + mouseX);
        float pmidy = 0.5f * (ppmouseY + mouseY);
        
        float diffx = pmouseX - pmidx;
        float diffy = pmouseY - pmidy;
        
        float x1 = pmouseX;
        float y1 = pmouseY;
        
        float x2 = midx + 0.5f * diffx;
        float y2 = midy + 0.5f * diffy;
        
        float x3 = mouseX;
        float y3 = mouseY;
        
        ppmouseX = pmouseX;
        ppmouseY = pmouseY;
        
        if (mousePressed)
        {
            //stroke(SomeColor);
            //bezier(x1,y1,x2,y2,x3,y3,x3,y3);
            //stroke(0,200,0);
            line(x1,y1,x3,y3);
        }
    }
}
