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
 * 
 * edited on 21.12.2019
 * introduced a new global variable, boolean t for configuring test scenarios.
 */
public class ParserTest 
{
    protected static DynamicArray a = new DynamicArray();
    private static boolean t = true;
    
    public static void main(String args[])
    {
        System.out.println("The parser test is about to begin.");
        
        // this is a test file, replace this value if some other file is needed
        String fname = "/Users/henrijuvonen/Documents/omat_roskat/banking_proj/engine-1.html";
        useInput(fname, t);
        System.out.println("Round 1 of processing: size of dynamic array is " + a.arraySize() + " and the dynamic array itself is" + a.toString());
        a = useInput(fname, t, a);
        System.out.println("Round 2 of processing: size of dynamic array is " + a.arraySize() + " and the dynamic array itself is" + a.toString());
        StatementParser.parser(a, t);
        System.out.println("Round 3 of processing: size of dynamic array is " + a.arraySize() + " and the dynamic array itself is" + a.toString());
        System.out.println("The parser test has finished.");
        System.out.println("a" + "b" + "c");
    }
}
