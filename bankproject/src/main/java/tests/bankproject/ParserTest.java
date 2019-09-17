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
 * 
 * edited 17.9.2019
 * further improved testing and reporting to terminal
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
        System.out.println("Round 1 of processing: size of dynamic array is " + a.arraySize() + " and the dynamic array itself is" + a.toString());
        a = useInput(fname, true, a);
        System.out.println("Round 2 of processing: size of dynamic array is " + a.arraySize() + " and the dynamic array itself is" + a.toString());
        StatementParser.parser(a, true);
        System.out.println("Round 3 of processing: size of dynamic array is " + a.arraySize() + " and the dynamic array itself is" + a.toString());
        System.out.println("The parser test has finished.");
        System.out.println("a" + "b" + "c");
    }
}
