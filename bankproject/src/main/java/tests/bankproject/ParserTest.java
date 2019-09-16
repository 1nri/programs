/*
 * Any license applies.
 */
package tests.bankproject;

import exercise.bankproject.DynamicArray;
import static exercise.bankproject.JavaProgramInput.useInput;
import exercise.bankproject.StatementParser;

/**
 *
 * @author henrijuvonen
 */
public class ParserTest 
{
    protected static DynamicArray a = new DynamicArray();
    
    public static void main(String args[])
    {
        System.out.println("The parser test is about to begin.");
        
        // this is a test file, replace this value if some other file is needed
        String fname = "/Users/henrijuvonen/Documents/omat_roskat/banking_proj/engine-1.html";
        useInput(fname, false);
        System.out.println("Round 1 of processing: " + a.toString());
        a = useInput(fname, true, a);
        System.out.println("Round 2 of processing: " + a.toString());
        StatementParser.parser(a, true);
        System.out.println("Round 3 of processing: " + a.toString());
        System.out.println("The parser test has finished.");
        System.out.println("a" + "b" + "c");
    }
}
