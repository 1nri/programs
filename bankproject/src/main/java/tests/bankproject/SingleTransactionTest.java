/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.bankproject;

import exercise.bankproject.SingleTransaction;
import java.util.Calendar;

/**
 *
 * @author henrijuvonen
 * 
 * edited 16.0.2019
 * reformatting. works perfectly.
 */
public class SingleTransactionTest {
    public static void main(String args[])
    {
        System.out.println("The single transaction test is about to begin.");
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
        boolean test = false;
        SingleTransaction testTransaction = new SingleTransaction(a, b, c, d, e, f, g, test);
        System.out.println(testTransaction.toString());
        System.out.println("The single statement test has finished.");
    }
}
