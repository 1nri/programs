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
 *
 * edited on 28.12.2019
 * began replacing system outputs with an independently made printing routine
 * TestOutputPrinter.
 */
public class ParserTest 
{
    protected static DynamicArray a = new DynamicArray();
    private static boolean t = true;
    private static String s = null;
    
    public static void main(String args[])
    {
        System.out.println("The parser test is about to begin.");
        
        // this is a test file, replace this value if some other file is needed
        String fname = "/Users/henrijuvonen/Documents/omat_roskat/banking_proj/engine-1.html";
        useInput(fname, t);
        s = "Round 1 of processing: size of dynamic array is " + a.arraySize() + " and the dynamic array itself is" + a.toString();
        System.out.println(s);
        // TestOutputPrinter.printALine(s);
        a = useInput(fname, t, a);
        s = "Round 2 of processing: size of dynamic array is " + a.arraySize() + " and the dynamic array itself is" + a.toString();
        System.out.println(s);
        // TestOutputPrinter.printALine(s);
        StatementParser.parser(a, t);
        s = "Round 3 of processing: size of dynamic array is " + a.arraySize() + " and the dynamic array itself is" + a.toString();
        System.out.println(s);
        // TestOutputPrinter.printALine(s);
        s = "The parser test has finished.";
        System.out.println(s);
        // TestOutputPrinter.printALine(s);
        s = "a" + "b" + "c";
        System.out.println(s);
        // TestOutputPrinter.printALine(s);
    }
}
