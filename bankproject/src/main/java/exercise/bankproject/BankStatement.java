package exercise.bankproject;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author henrijuvonen
 * 
 * edited 16.9.2019 
 * reformatting
 */
public class BankStatement {
    
    /**
     * necessary variables to store data from a bank statement
     */
    private Calendar firstDateOfStatement;
    private Calendar lastDateOfStatement;
    private String nameOfStatement;
    private String charSetOfStatement;
    private String mediaTypeOfStatement;
    private int numberOfTransactions;
    private int valueOfTransactions;
    private String nameOfBank;
    private String streetAddressOfBank;
    private String phoneNumberOfBank;
    private DynamicArray contentOfStatement;
    private int totalWithdrawals;
    private int totalDeposits;
    private String ownerOfBankAccount;
    
    /**
     * these are mainly used for determining the execution level, 
     * test must be true when testing
     */
    private boolean test;
    private String firstDateOutput;
    private String lastDateOutput;
    private String statementNameOutput;
    private String charSetOutput;
    private String mediaTypeOutput;
    private String numberOfTransactionsOutput;
    private String valueOfTransactionsOutput;
    private String bankNameOutput;
    private String addressOfBankOutput;
    private String phoneNumberOfBankOutput;
    private String totalAmountOfWithdrawalsOutput;
    private String totalAmountOfDepositsOutput;
    private String contentOfStatementOutput;
    private String ownerOfBankAccountOutput;
    
    /**
     * default constructor
     */
    public BankStatement()
    {
        this.ownerOfBankAccountOutput = "The owner of the bank accounts is : ";
        this.contentOfStatementOutput = "The content of this statement is : ";
        this.totalAmountOfDepositsOutput = "The total amounts of deposits in this statement is : ";
        this.totalAmountOfWithdrawalsOutput = "The total amount of withdrawals in this statement is : ";
        this.phoneNumberOfBankOutput = "The phone number of the bank providing this statement is : ";
        this.addressOfBankOutput = "The street address of the bank providing this statement is : ";
        this.bankNameOutput = "The name of the bank providing this statement is : ";
        this.valueOfTransactionsOutput = "The value of transactions on this statement is : ";
        this.numberOfTransactionsOutput = "The number of transactions on this statement is : ";
        this.mediaTypeOutput = "The media type of this statement is : ";
        this.charSetOutput = "The character set of this statement is : ";
        this.statementNameOutput = "The name of this statement is : ";
        this.lastDateOutput = "The last date of this statement is : ";
        this.firstDateOutput = "The first date of this statement is : ";
        this.test = true;
        contentOfStatement = new DynamicArray();
        firstDateOfStatement = null;
        lastDateOfStatement = null;
        nameOfStatement = null;
        charSetOfStatement = null;
        mediaTypeOfStatement = null;
        numberOfTransactions= 0;
        nameOfBank = null;
        streetAddressOfBank = null;
        phoneNumberOfBank = null;
    }
    
    /**
     * this constructor is supposed to be used when invoking a bank statement 
     * object
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @param g
     * @param h
     * @param i
     * @param j
     * @param k
     * @param l
     * @param m
     * @param test 
     */
    public BankStatement(Calendar a, Calendar b, String c, String d, String e, int f, String g, String h, String i, DynamicArray j, int k, int l, String m, boolean test)
    {
        
        /**
         * testing whether we are testing
         */
        if(test == true)
        {
            this.ownerOfBankAccountOutput = "The owner of the bank accounts is : ";
            this.contentOfStatementOutput = "The content of this statement is : ";
            this.totalAmountOfDepositsOutput = "The total amounts of deposits in this statement is : ";
            this.totalAmountOfWithdrawalsOutput = "The total amount of withdrawals in this statement is : ";
            this.phoneNumberOfBankOutput = "The phone number of the bank providing this statement is : ";
            this.addressOfBankOutput = "The street address of the bank providing this statement is : ";
            this.bankNameOutput = "The name of the bank providing this statement is : ";
            this.valueOfTransactionsOutput = "The value of transactions on this statement is : ";
            this.numberOfTransactionsOutput = "The number of transactions on this statement is : ";
            this.mediaTypeOutput = "The media type of this statement is : ";
            this.charSetOutput = "The character set of this statement is : ";
            this.statementNameOutput = "The name of this statement is : ";
            this.lastDateOutput = "The last date of this statement is : ";
            this.firstDateOutput = "The first date of this statement is : ";
            this.test = test;
        }
        
        /**
         * actual constructor logic
         */
        if(setFirstDateOfStatement(a) && setLastDateOfStatement(b) && setNameOfStatement(c) && 
                setCharSet(d) && setMediaType(e) && setNumberOfTransactions(f) && setNameOfBank(g) &&
                setStreetAddress(h) && setPhoneNumber(i) && setContentOfStatement(j) &&
                setTotalAmountOfWithdrawals(k) && setTotalAmountOfDeposits(l) && 
                setOwnerOfBankAccount(m) && setValueOfTransactions())
        {
        }
        
        /**
         * standard notification of an incorrect functionality, probably unintended 
         * input data
         */
        else System.out.println("Couldn't create bank statement with provided input.");
    }
    
    /** 
     * setter
     * @param dateToSet
     * @return boolean if successful
     */
    private boolean setFirstDateOfStatement(Calendar dateToSet)
    {
        
        if(dateToSet != null)
        {
            firstDateOfStatement = dateToSet;
            return true;
        }
        
        else if(this.test == true)
        {
            System.out.println("Couldn't initialize first date of bank statement with value: "
            + dateToSet);
            return false;
        }
        
        else return false;
    }

    /**
     * setter
     * @param dateToSet
     * @return boolean if successful
     */
    private boolean setLastDateOfStatement(Calendar dateToSet)
    {
        
        if(dateToSet != null)
        {
            lastDateOfStatement = dateToSet;
            return true;
        }
        
        else if(this.test == true) {
            System.out.println("Couldn't initialize last date of bank statement with value: "
            + dateToSet);
            return false;
        }
        
        else return false;
    }

    /**
     * setter
     * @param nameToSet
     * @return boolean if successful
     */
    private boolean setNameOfStatement(String nameToSet) 
    {
        
        if(nameToSet != null)
        {
            nameOfStatement = nameToSet;
            return true;
        }
        
        else if(this.test == true)
        {
            System.out.println("Couldn't initialize name of bank statement with value: "
            + nameToSet);
            return false;
        }
        
        else return false;
    }

    /**
     * setter
     * @param charSetToSet
     * @return boolean if successful
     */
    private boolean setCharSet(String charSetToSet) 
    {
        
        if(charSetToSet != null)
        {
            charSetOfStatement = charSetToSet;
            return true;
        }
        
        else if(this.test == true)
        {
            System.out.println("Couldn't initialize character set of bank statement with value: "
            + charSetToSet);
            return false;
        }
        
        else return false;
    }

    /**
     * setter
     * @param mediaTypeToSet
     * @return boolean if successful
     */
    private boolean setMediaType(String mediaTypeToSet) 
    {
        
        if(mediaTypeToSet != null)
        {
            mediaTypeOfStatement = mediaTypeToSet;
            return true;
        }
        
        else if(this.test == true){
            System.out.println("Couldn't initialize media type of bank statement with value: "
            + mediaTypeToSet);
            return false;
        }
        
        else return false;
    }

    /**
     * setter
     * @param statementCount
     * @return boolean if successful
     */
    private boolean setNumberOfTransactions(int statementCount) 
    {
        
        if(statementCount >= 0)
        {
            numberOfTransactions = statementCount;
            return true;
        }
        
        else if(this.test == true)
        {
            System.out.println("Couldn't initialize number of transactions in bank statement with value: "
            + statementCount);
            return false;
        }
        
        else return false;
    }

    /**
     * setter
     * @param nameToSet
     * @return boolean if successful
     */
    private boolean setNameOfBank(String nameToSet) 
    {
        
        if(nameToSet != null)
        {
            nameOfBank = nameToSet;
            return true;
        }
        
        else if(this.test == true)
        {
            System.out.println("Couldn't initialize name of bank with value: "
            + nameToSet);
            return false;
        }
        
        else return false;
    }

    /**
     * setter
     * @param addressToSet
     * @return boolean if successful
     */
    private boolean setStreetAddress(String addressToSet) 
    {
        
        if(addressToSet != null)
        {
            streetAddressOfBank = addressToSet;
            return true;
        }
        
        else if(this.test == true)
        {
            System.out.println("Couldn't initialize street address of bank statement with value: "
            + addressToSet);
            return false;
        }
        
        else return false;
    }

    /**
     * setter
     * @param phoneNumberToSet
     * @return boolean if successful
     */
    private boolean setPhoneNumber(String phoneNumberToSet) 
    {
        
        if(phoneNumberToSet != null)
        {
            phoneNumberOfBank = phoneNumberToSet;
            return true;
        }
        
        else if(this.test == true)
        {
            System.out.println("Couldn't initialize phone number of the bank statement with value: "
            + phoneNumberToSet);
            return false;
        }
        
        else return false;
    }

    /**
     * setter
     * @param statementContents
     * @return boolean if successful
     */
    private boolean setContentOfStatement(DynamicArray statementContents) 
    {
        
        if(statementContents != null)
        {
            contentOfStatement = statementContents;
            return true;
        }
        
        else if (this.test == true)
        {
            System.out.println("Couldn't initialize contents of bank statement with value: "
            + statementContents);
            return false;
        }
        
        else return false;
    }
    
    /**
     * setter
     * @param amountToSet
     * @return boolean if successful
     */
    private boolean setTotalAmountOfWithdrawals(int amountToSet) 
    {
        
        if(amountToSet >= 0)
        {
            totalWithdrawals = amountToSet;
            return true;
        }
        
        else if(this.test == true)
        {
            System.out.println("Couldn't set total amount of withdrawals in bank statement with value: "
            + amountToSet);
            return false;
        }
        
        else return false;
    }

    /**
     * setter
     * @param amountToSet
     * @return boolean if successful
     */
    private boolean setTotalAmountOfDeposits(int amountToSet) 
    {
        
        if(amountToSet >= 0)
        {
            totalDeposits = amountToSet;
            return true;
        }
        
        else if(this.test == true)
        {
            System.out.println("Couldn't initialize total amount of deposits in bank statement with value: "
            + amountToSet);
            return false;
        }
        
        else return false;
    }
    
    /**
     * setter
     * @return boolean if successful
     */
    private boolean setValueOfTransactions()
    {
        valueOfTransactions = getTotalAmountOfWithdrawals() + getTotalAmountOfDeposits();
        return true;
    }
    
    /** 
     * setter
     * @param nameToBeSet
     * @return boolean if successful
     */
    private boolean setOwnerOfBankAccount(String nameToBeSet) 
    {
        
        if(nameToBeSet != null){
            ownerOfBankAccount = nameToBeSet;
            return true;
        }
        
        else if (this.test == true)
        {
            System.out.println("Couldn't initialize owner of bank account with value: "
            + nameToBeSet);
            return false;
        }
        
        else return false;
    }
    
    /**
     * getter
     * @return String
     */
    public String getFirstDate()
    {
        int dayOfYear = firstDateOfStatement.get(Calendar.DAY_OF_MONTH);
        int monthOfYear = firstDateOfStatement.get(Calendar.MONTH) + 1;
        int valueOfYear = firstDateOfStatement.get(Calendar.YEAR);
        return dayOfYear + "." + monthOfYear + "." + valueOfYear;
    }
    
    /**
     * getter
     * @return String
     */
    public String getLastDate()
    {
        int dayOfYear = lastDateOfStatement.get(Calendar.DAY_OF_MONTH);
        int monthOfYear = lastDateOfStatement.get(Calendar.MONTH) + 1;
        int valueOfYear = lastDateOfStatement.get(Calendar.YEAR);
        return dayOfYear + "." + monthOfYear + "." + valueOfYear;
    }
    
    /**
     * getter
     * @return int
     */
    public int getNumberOfTransactions()
    {
        return numberOfTransactions;
    }
    
    /**
     * getter
     * @return String
     */
    public String getNameOfStatement()
    {
        return nameOfStatement;
    }
    
    /**
     * getter
     * @return String
     */
    public String getCharSet()
    {
        return charSetOfStatement;
    }
    
    /**
     * getter
     * @return String
     */
    public String getMediaTypeOfStatement()
    {
        return mediaTypeOfStatement;
    }
    
    /**
     * getter
     * @return String
     */
    public String getNameOfBank()
    {
        return nameOfBank;
    }
    
    /**
     * getter
     * @return String
     */
    public String getStreetAddressOfBank()
    {
        return streetAddressOfBank;
    }
    
    /**
     * getter
     * @return String
     */
    public String getOwnerOfBankAccount()
    {
        return ownerOfBankAccount;
    }
    
    /**
     * getter
     * @return String
     */
    public String getPhoneNumberOfBank()
    {
        return phoneNumberOfBank;
    }
    
    /**
     * getter
     * @return DynamicArray
     */
    public DynamicArray getContentOfStatement()
    {
        return contentOfStatement;
    }

    /**
     * getter
     * @return int
     */
    public int getTotalAmountOfWithdrawals() 
    {
        return totalWithdrawals;
    }
    
    /**
     * getter
     * @return int
     */
    public int getTotalAmountOfDeposits() 
    {
        return totalDeposits;
    }
    
    /**
     * getter 
     * @return int
     */
    public int getValueOfTransactions()
    {
        return valueOfTransactions;
    }

    /**
     * overridden toString method of String object
     * @return String
     */
    @Override
    public String toString()
    {
        
        if(this.test == true)
        {
            String output = firstDateOutput + getFirstDate() + System.lineSeparator() + 
                    lastDateOutput + getLastDate() + System.lineSeparator() + 
                    statementNameOutput + getNameOfStatement() + System.lineSeparator() + 
                    charSetOutput + getCharSet() + System.lineSeparator() + 
                    mediaTypeOutput + getMediaTypeOfStatement() + System.lineSeparator() + 
                    bankNameOutput + getNameOfBank() + System.lineSeparator() + 
                    addressOfBankOutput + getStreetAddressOfBank() + System.lineSeparator() + 
                    phoneNumberOfBankOutput + getPhoneNumberOfBank() + System.lineSeparator() + 
                    ownerOfBankAccountOutput + getOwnerOfBankAccount() + System.lineSeparator() + 
                    numberOfTransactionsOutput + getNumberOfTransactions() + System.lineSeparator() + 
                    valueOfTransactionsOutput + getValueOfTransactions() + System.lineSeparator() + 
                    totalAmountOfWithdrawalsOutput + getTotalAmountOfWithdrawals() + System.lineSeparator() + 
                    totalAmountOfDepositsOutput + getTotalAmountOfDeposits() + System.lineSeparator() + 
                    contentOfStatementOutput + getContentOfStatement() + System.lineSeparator();
            return output;
        }
        
        else return "This is not a test, so no direct printing.";
    }
}
