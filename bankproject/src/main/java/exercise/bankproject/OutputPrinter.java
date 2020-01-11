/*
 * Any license applies.
 */

package exercise.bankproject;

import tests.bankproject.TestOutputPrinter;

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
public class OutputPrinter {
    
    public static void printALine(String s, boolean t){
        
        // for now, this will be just a replacement of System.out.println().
        if(t == true){
        
            if(s != null)
                TestOutputPrinter.printALine(s);

            else
                System.out.println("String to be printed was null, no test "
                        + "print.");
        }
    }
    
    public static void printAnEmptyLine(String s, boolean t){
        
// for now, this will be just a replacement of System.out.println().
        if(t == true){
            
            TestOutputPrinter.printAnEmptyLine(s);

        }
    }
    
    public static void printRegardlessOfLines(String s, boolean t){
        
        // for now, this will be just a replacement of System.out.print().
        // once a better solution comes up (see above), remove the line below
        if(t == true){
        
            if(s != null)
                TestOutputPrinter.printRegardlessOfLines(s);

            else
                System.out.print("String to be printed was null, no test "
                        + "print.");
        }
    }
}
