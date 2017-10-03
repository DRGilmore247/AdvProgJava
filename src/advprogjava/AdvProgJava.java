/*
 * The open Kattis/ git homework.
 * Problem URL: https://open.kattis.com/problems/cold
 * David Gilmore
 * 3 Oct 2017
 */
package advprogjava;

/**
 *
 * @author David
 */
public class AdvProgJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AdvProgJava program = new AdvProgJava();
        program.run(args);
    }
    
    public void run(String[] args)
    {
        int coldDays = daysBelow(args);
        System.out.println("Days Below Zero: " + coldDays);
    }
    
    public int daysBelow(String[] args)
    {
        int count = 0;
        
        for (int i = 0; i < args.length; i++)
        {
            int temp = 0;
            
            try
            {
                temp = Integer.parseInt(args[i]);
            }
            catch (Exception ex)
            {
                System.out.println("Unable to convert to integer.");
            }
            
            if (temp < 0)
            {
                count++;
            }    
                 
        }
        
        
        
      return count;  
    }
}
