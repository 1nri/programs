/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.bankproject;

import exercise.bankproject.BankStatement;
import exercise.bankproject.DynamicArray;
import java.util.Calendar;

/**
 *
 * @author henrijuvonen
 */
public class BankStatementTest {
    
    public static void main(String args[])
    {
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
        testStatement = new BankStatement(a, b, c, d, e, f, g, h, i, j, k, l, m, true);
        System.out.println(testStatement);
    }
    
}
