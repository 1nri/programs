/*
 * Any license applies.
 */
package tests.bankproject;

/**
 *
 * @author henrijuvonen
 * 
 * created on 28.12.2019
 * just a test to test the printer test
 */
public class TestPrinterTest {
    public static void main(String args[]){
        String s = null;
        TestOutputPrinter.printALine(s);
        TestOutputPrinter.printAnEmptyLine(s);
        TestOutputPrinter.printRegardlessOfLines(s);
        s = "testis";
        TestOutputPrinter.printALine(s);
        TestOutputPrinter.printAnEmptyLine(s);
        TestOutputPrinter.printRegardlessOfLines(s);
    }
}
