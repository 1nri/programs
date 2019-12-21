/*
 * Any license applies.
 */

package tests.bankproject;

import exercise.bankproject.BankStatement;
import exercise.bankproject.DynamicArray;
import java.util.Calendar;

/**
 *
 * @author henrijuvonen
 * 
 * edited 16.9.2019
 * reformatting. works perfectly.
 * 
 * edited on 21.12.2019
 * introduced a new global variable, boolean t for configuring test scenarios.
 */
public class BankStatementTest 
{
    
    private static boolean t = true;
    
    public static void main(String args[])
    {
        System.out.println("The bank statement test is about to begin.");
        // Calendar's months begin from 0??
        Calendar a = Calendar.getInstance();
        a.set(2017, 7, 1);
        Calendar b;
        b = Calendar.getInstance();
        b.set(2017, 7, 31);
        String c;
        c = "Tiliote - 2017.08";
        String d;
        d = "charset=iso-8859-1";
        String e;
        e = "print";
        int f = 0;
        String g;
        g = "Aktia Pankki Oyj";
        String h;
        h = "PL 207 00101 Helsinki";
        String i ;
        i = "PUH.  010 247 010";
        DynamicArray j = new DynamicArray();
        int k = 0;
        int l = 0;
        String m;
        m = "Juvonen Henri Arimo";
        BankStatement testStatement;
        testStatement = new BankStatement(a, b, c, d, e, f, g, h, i, j, k, l, m, t);
        System.out.println(testStatement);
        System.out.println("The bank statement test has finished.");
    }
}
