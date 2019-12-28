/*
 * Any license applies.
 */

package tests.bankproject;

/**
 *
 * @author henrijuvonen
 * 
 * created 28.12.2019
 * created this printing class in order to test printing solutions ie. outputs
 * -initial idea was to prepare a window in order to see output outside of 
 *  netbeans
 * -second idea was to test printing in general and finally separating test
 *  result outputs from the core backend modules
 * -third idea was to practice printing output using other classes than the ones
 *  which process data ie. make the code more modular and, in a sense, granular

 */
public class TestOutputPrinter {
    
    public void printALine(String s){
        
        // for now, this will be just a replacement of System.out.println()
        // once a better solution comes up, remove the line below
        if(s != null)
            System.out.println(s);
        else
            System.out.println("String to be printed was null, no print.");
        
    }
    
    public void printRegardlessOfLines(String s){
        
        // for now, this will be just a replacement of System.out.print()
        
    }
}
