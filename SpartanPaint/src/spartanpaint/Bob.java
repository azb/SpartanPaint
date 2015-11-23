/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartanpaint;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author C16603
 */
public class Bob {
        
    public Bob(int number)
    {
        getQuote(number);
    }
    
    private String getQuote(int number)
    {
        String quote = null;
        
        try
        {
            Scanner s = new Scanner(new File("quotes.txt"));
            
            int x = 0;
            while(x < number)
            {
                s.nextLine();
            }
            
            quote = s.nextLine();
        }
        catch(Exception e)
        {
            System.out.println("File not found!");
        }
        
        return null;   
    }
    
}
