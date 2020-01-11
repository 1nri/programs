/*
 * Any license applies.
 */

package tests.bankproject;

import exercise.bankproject.SingleTransaction;
import java.util.Calendar;

/**
 *
 * @author henrijuvonen
 * 
 * edited 16.9.2019
 * reformatting. works perfectly.
 * 
 * edited on 21.12.2019
 * changed the date of first reformatting, 0 -> 9. probably just a typo.
 * introduced a new global variable, boolean t for configuring test scenarios.
 * 
 * edited on 28.12.2019
 * began replacing system outputs with an independently made printing routine
 * TestOutputPrinter.
 */
public class SingleTransactionTest {
    
    private static boolean t = true;
    private static String s = null;
    
    public static void main(String args[])
    {
        s = "The single transaction test is about to begin.";
        System.out.println(s);
        
        //TestOutputPrinter.printALine(s);
        /**
         * setTypeOfPayment(a) && setRecipient(b) && setPayee(c) && 
                setNameOfStatement(d) && setDateOfPayment(e) && 
                setValueOfTransaction(f) && setNumberOfOrderOnStatement(g)
         */
        
        // test value for type of payment
        char a = 'w';
        
        // test value for date of payment
        // Calendar's months begin from 0??
        Calendar e;
        e = Calendar.getInstance();
        e.set(2017, 7, 31);
        
        // test value for name of recipient
        String b;
        b = "test recipient";
        
        // test value for payee
        String c;
        c = "test payee";
        
        // test value for name of statement
        String d;
        d = "test statement";
        
        //test value of transaction
        double f = 2.0;
        
        // test value for number of order
        int g;
        g = 1;
        
        // test value for test value (:D)
        // boolean test = t;
        SingleTransaction testTransaction = new SingleTransaction(a, b, c, d, e, f, g, t);
        
        // assign String to be printed to a variable to be passed to printing 
        // routine
        s = testTransaction.toString();
        //System.out.println(testTransaction.toString());
        TestOutputPrinter.printALine(s);
        s = "The single statement test has finished.";
        //System.out.println("The single statement test has finished.");
        TestOutputPrinter.printALine(s);
    }
}
