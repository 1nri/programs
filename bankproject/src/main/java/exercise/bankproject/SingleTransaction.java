/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise.bankproject;

import java.util.Calendar;

/**
 *
 * @author henrijuvonen
 * 
 * edited 16.9.2019
 * reformatting, added comments
 */
public class SingleTransaction 
{
    
    /**
     * necessary variables to store data of transaction
     */
    private char typeOfPayment;
    private String recipient;
    private String payee;
    private String nameOfStatement;
    private Calendar dateOfPayment;
    private double valueOfTransaction;
    private int numberOfOrderOnStatement;
    
    /**
     * these are mainly useful for testing purposes
     */
    private boolean test;
    private final String typeOfPaymentOutput;
    private final String recipientOutput;
    private final String payeeOutput;
    private final String nameOfStatementOutput;
    private final String dateOfPaymentOutput;
    private final String valueOfTransactionOutput;
    private final String numberOfOrderOnStatementOutput;
    private final String testValueOutput;
    
    /**
     * default constructor
     */
    public SingleTransaction()
    {
        this.dateOfPayment = null;
        this.recipient = null;
        this.payee = null;
        this.typeOfPayment = ' ';
        this.nameOfStatement = null;
        this.valueOfTransaction = 0;
        this.numberOfOrderOnStatement = 0;
        this.test = true;
        typeOfPaymentOutput = "The type of this payment is : ";
        recipientOutput = "The recipient of this payment is : ";
        payeeOutput = "The payee of this payment is : ";
        nameOfStatementOutput = "The name of the related statement is : ";
        dateOfPaymentOutput = "The date of payment is : ";
        valueOfTransactionOutput = "The value of this transaction is : ";
        numberOfOrderOnStatementOutput = "The number of order of this transaction on the related statement is : ";
        testValueOutput = "The test value is set to : ";     
    }
    
    /**
     * a proper constructor
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @param g
     * @param test 
     */
    public SingleTransaction(char a, String b, String c, String d, Calendar e, double f, int g, boolean test)
    {
        setTestValue(test);
        typeOfPaymentOutput = "The type of this payment is : ";
        recipientOutput = "The recipient of this payment is : ";
        payeeOutput = "The payee of this payment is : ";
        nameOfStatementOutput = "The name of the related statement is : ";
        dateOfPaymentOutput = "The date of payment is : ";
        valueOfTransactionOutput = "The value of this transaction is : ";
        numberOfOrderOnStatementOutput = "The number of order of this transaction on the related statement is : ";
        testValueOutput = "The test value is set to : ";
        
        if(setTypeOfPayment(a) && setRecipient(b) && setPayee(c) && 
                setNameOfStatement(d) && setDateOfPayment(e) && 
                setValueOfTransaction(f) && setNumberOfOrderOnStatement(g)){
            
        }
        else System.out.println("Couldn't create bank statement with provided input.");
    }
    
    /**
     * setter
     * @param t
     * @return boolean if successful
     */
    private boolean setTestValue(boolean t)
    {
        this.test = t;
        return true;
    }
    
    /**
     * getter
     * @return boolean
     */
    public boolean getTestValue()
    {
        return this.test;
    }
    
    /**
     * setter 
     * @param s
     * @return boolean if successful
     */
    private boolean setTypeOfPayment(char s)
    {
        
        if(s == 'd' || s == 'w')
        {
            this.typeOfPayment = s;
            return true;
        }
        
        else if(!this.test)
        {
            System.out.println("Couldn't initialize type of payment with value: "
            + s + System.lineSeparator() + "The value must be 'w' or 'd'.");
            return false;
        }
        
        else return false;
    }
    
    /**
     * getter
     * @return char
     */
    public char getTypeOfPayment()
    {
        return this.typeOfPayment;
    }
    
    /**
     * setter
     * @param s
     * @return boolean if successful
     */
    private boolean setRecipient(String s)
    {
        
        if(s != null)
        {
            this.recipient = s;
            return true;
        }
        
        else if(!this.test)
        {
            System.out.println("Couldn't initialize recipient with value: " + s);
            return false;
        }
        
        else return false;
    }

    /**
     * getter 
     * @return String
     */
    public String getRecipient()
    {
        return this.recipient;
    }
    
    /**
     * setter
     * @param s
     * @return boolean if successful
     */
    private boolean setPayee(String s)
    {
        
        if(s!=null)
        {
            this.payee = s;
            return true;
        }
        
        else if(!this.test)
        {
            System.out.println("Couldn't initialize payee with value: " + s);
            return false;
        }
        
        else return false;
    }
    
    /**
     * getter
     * @return String
     */
    public String getPayee()
    {
        return this.payee;
    }
    
    /**
     * setter
     * @param s
     * @return boolean if successful
     */
    private boolean setNameOfStatement(String s)
    {
        
        if(s != null)
        {
            this.nameOfStatement = s;
            return true;
        }
        
        else if(!this.test)
        {
            System.out.println("Couldn't initialize name of statement with value: "
            + s);
            return false;
        }
        
        else return false;
    }
    
    /**
     * getter 
     * @return String 
     */
    public String getNameOfStatement()
    {
        return this.nameOfStatement;
    }
    
    /**
     * setter
     * @param c
     * @return boolean if successful
     */
    private boolean setDateOfPayment(Calendar c)
    {
        
        if(c != null)
        {
            this.dateOfPayment = c;
            return true;
        }
        
        else if(!this.test)
        {
            System.out.println("Couldn't initialize date of payment with value: "
            + c);
            return false;
        }
        
        else return false;
    }
    
    /** 
     * getter
     * @return String
     */
    public String getDateOfPayment()
    {
        int dayOfYear = this.dateOfPayment.get(Calendar.DAY_OF_MONTH);
        int monthOfYear = this.dateOfPayment.get(Calendar.MONTH) + 1;
        int valueOfYear = this.dateOfPayment.get(Calendar.YEAR);
        return dayOfYear + "." + monthOfYear + "." + valueOfYear;
    }
    
    /**
     * setter
     * @param d
     * @return boolean if successful
     */
    private boolean setValueOfTransaction(double d)
    {
        
        if(d != 0)
        {
            this.valueOfTransaction = d;
            return true;
        }
        
        else if(!this.test)
        {
            System.out.println("Couldn't initialize date of payment with value: "
            + d);
            return false;
        }
        
        else return false;
    }
    
    /**
     * getter
     * @return double
     */
    public double getValueOfTransaction()
    {
        return this.valueOfTransaction;
    }

    /**
     * setter 
     * @param i
     * @return boolean if successful
     */
    private boolean setNumberOfOrderOnStatement(int i)
    {
        
        if(i != 0)
        {
            this.numberOfOrderOnStatement = i;
            return true;
        }
        
        else if(!this.test)
        {
            System.out.println("Couldn't initialize date of payment with value: "
            + i);
            return false;
        }
        
        else return false;
    }
    
    /**
     * getter
     * @return int
     */
    public int getNumberOfOrderOnStatement()
    {
        return this.numberOfOrderOnStatement;
    }

    /**
     * overridden toString, useful for testing purposes
     * @return String
     */
    @Override
    public String toString()
    {
        
        if(this.test == true)
        {
            String output = dateOfPaymentOutput + getDateOfPayment() + System.lineSeparator() + 
                    recipientOutput + getRecipient() + System.lineSeparator() +
                    payeeOutput + getPayee() + System.lineSeparator() +
                    nameOfStatementOutput + getNameOfStatement() + System.lineSeparator() +
                    typeOfPaymentOutput + getTypeOfPayment() + System.lineSeparator() +
                    valueOfTransactionOutput + getValueOfTransaction() + System.lineSeparator() +
                    numberOfOrderOnStatementOutput + getNumberOfOrderOnStatement() + System.lineSeparator() +
                    testValueOutput + getTestValue() + System.lineSeparator();
            return output;
        }
        
        else return "This is not a test, so no direct printing.";
    }
}
