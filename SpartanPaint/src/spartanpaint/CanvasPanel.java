package spartanpaint;

import processing.core.*;
import java.util.Random;

public class CanvasPanel
extends PAppletPanel<CanvasPanel.Applet>
{
    @Override
    protected Applet CreatePApplet()
    {
        return new Applet();
    }
    public class Applet extends PApplet
    {
        int ppmouseX;
        int ppmouseY;

        //Array of news headlines
        String[] headlines;

        PFont font; //Global font variable
        float fontX; //horizontal location of headline
        int index = 0; //Quote currently displayed
                
        public int SomeColor;
        public int count = 0;
        public int quoteCounter = 0;
        
        public String currentTool = "";
        public boolean pencil = false;
        public boolean fireworks = false;
        
        @Override
        public void setup()
        {
            //frameRate(24);
            //size(200, 200);
            background(0);
            ppmouseX = mouseX;
            ppmouseY = mouseY;
            SomeColor = color(255, 0, 0);
            
            //Font for quotes
            font = createFont("Arial",16,true);
            fontX = width;   

            //Populate headlines
            getHeadlines();
        }
        
        @Override
        public void draw()
        {
            //Rectangle fill (rect that fixes text animation)
            fill(0);
            noStroke();
            rect(0,height-40,width,25);
            
            //Text fill
            fill(255);
            
            // Display headline at x  location
            textFont(font,16);        
            textAlign(LEFT);
            text(headlines[index],fontX,height-25); 
            
            // Decrement x
            fontX = fontX - 2;

            // If x is less than the negative width, 
            // then it is off the screen
            float w = textWidth(headlines[index]);
            
            //Update font
            if (fontX < -w) 
            {
              fontX = width; 
              index = (index + 1) % headlines.length;
            }    
            
            switch(currentTool)
            {
                case "pencil":
                    fill(0, 2 * (frameCount % 2));
                    rect(0,0,width,height);
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
                        stroke(SomeColor);
                        bezier(x1,y1,x2,y2,x3,y3,x3,y3);
                        stroke(0,200,0);
                        line(x1,y1,x3,y3);  
                    }
                    break;
                
                case "splat":
                    if (mousePressed)
                    {
                        count++;
                        
                        //makes it so splat occurs only once per click
                        if(count < 2)
                        {
                            Random rand = new Random();

                            //creates 15 splats! OH FUCKING WOW
                            for(int i = 0; i < 15; i++)
                            {
                                int x = rand.nextInt(101); //distance x from mouse
                                int y = rand.nextInt(101); //distance y from mouse
                                int xNeg = rand.nextInt(2); //50-50 chance to be left or right of mouse
                                int yNeg = rand.nextInt(2); //50-50 chance to be above or below mouse

                                if(xNeg == 1) //set negative if equal to 1 (left of mouse)
                                    x *= -1;
                                if(yNeg == 1) //set negative if equal to 1 (above mouse)
                                    y *= -1;
                                
                                fill(255,255,255); //fill color of paint splat
                                ellipse(x+mouseX,y+mouseY,15,15); //coordinates of splatter, width, height
                            }
                        }
                    }
                    break;  
                default:
                    //do nothing
                    break;
            }
        }
        
        public void mouseReleased()
        {
            count = 0; //reset counter
        }
            
        //Pencil tool selected
        public void pencilTool()
        {
            currentTool = "pencil";
        }
            
        //Fireworks tool selected
        public void splatTool()
        {
            currentTool = "splat";
        }
        
        //Calls another class to get and fill array
        public void getHeadlines()
        {           
            //Call QuoteGrab class and get number of quotes
            QuoteGrab q = new QuoteGrab(); 
            int lineCount = q.getLineCount();
                        
            //Set index count to number of quotes. Makes it so we don't have to manually count quotes
            Random r = new Random();        
            index = r.nextInt(lineCount)+1; 
            
            //Declare the size of the array "headlines"
            headlines = new String[lineCount];
            
            //Assign each element of the array to a quote
            for(int i = 0; i < lineCount; i++)
            {
                String quote = q.getQuote(i);
                headlines[i] = quote;
            }
        }
    }
}