package spartanpaint;

import java.io.File;
import java.util.Scanner;
import java.util.Random;

public class QuoteGrab {

    private static String output;
    private static int count;
    
    public QuoteGrab()
    {
        output = null;
        count = 0;
    }
    
    public String getQuote(int y)
    {
        try
        {
            Scanner sc = new Scanner(new File("/Users/C16603/Documents/SpartanPaint/SpartanPaint/src/spartanpaint/Quotes.txt"));
            
            int x = 0;
            while(x != y+1)
            {
                output = sc.nextLine();
                x++;
            }
            
            sc.close();
        }
        catch(Exception E)
        {
            System.out.println("File Not Found!");
        }
        return output;
    }
    
    public int getLineCount()
    {
        try
        {
            Scanner sc = new Scanner(new File("/Users/C16603/Documents/SpartanPaint/SpartanPaint/src/spartanpaint/Quotes.txt"));

            while(sc.hasNextLine())
            {
                count++;
                sc.nextLine();
            }
            
            sc.close();
        }
        catch(Exception E)
        {
            System.out.println("File Not Found!");
        }
        
        return count;
    }
}
