package exercise.bankproject;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author henrijuvonen
 */
public class BankStatement {
    
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
    private boolean test;
    private final String firstDateOutput;
    private final String lastDateOutput;
    private final String statementNameOutput;
    private final String charSetOutput;
    private final String mediaTypeOutput;
    private final String numberOfTransactionsOutput;
    private final String valueOfTransactionsOutput;
    private final String bankNameOutput;
    private final String addressOfBankOutput;
    private final String phoneNumberOfBankOutput;
    private final String totalAmountOfWithdrawalsOutput;
    private final String totalAmountOfDepositsOutput;
    private final String contentOfStatementOutput;
    private final String ownerOfBankAccountOutput;
    
    public BankStatement(){
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
    
    public BankStatement(Calendar a, Calendar b, String c, String d, String e, int f, String g, String h, String i, DynamicArray j, int k, int l, String m, boolean test){
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
        if(setFirstDateOfStatement(a) && setLastDateOfStatement(b) && setNameOfStatement(c) && 
                setCharSet(d) && setMediaType(e) && setNumberOfTransactions(f) && setNameOfBank(g) &&
                setStreetAddress(h) && setPhoneNumber(i) && setContentOfStatement(j) &&
                setTotalAmountOfWithdrawals(k) && setTotalAmountOfDeposits(l) && 
                setOwnerOfBankAccount(m) && setValueOfTransactions()){
        }
        else System.out.println("Couldn't create bank statement with provided input.");
    }
    
    private boolean setFirstDateOfStatement(Calendar dateToSet){
        if(dateToSet != null){
            firstDateOfStatement = dateToSet;
            return true;
        }
        else if(this.test == true) {
            System.out.println("Couldn't initialize first date of bank statement with value: "
            + dateToSet);
            return false;
        }
        else return false;
    }

    private boolean setLastDateOfStatement(Calendar dateToSet) {
        if(dateToSet != null){
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

    private boolean setNameOfStatement(String nameToSet) {
        if(nameToSet != null){
            nameOfStatement = nameToSet;
            return true;
        }
        else if(this.test == true){
            System.out.println("Couldn't initialize name of bank statement with value: "
            + nameToSet);
            return false;
        }
        else return false;
    }

    private boolean setCharSet(String charSetToSet) {
        if(charSetToSet != null){
            charSetOfStatement = charSetToSet;
            return true;
        }
        else if(this.test == true){
            System.out.println("Couldn't initialize character set of bank statement with value: "
            + charSetToSet);
            return false;
        }
        else return false;
    }

    private boolean setMediaType(String mediaTypeToSet) {
        if(mediaTypeToSet != null){
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

    private boolean setNumberOfTransactions(int statementCount) {
        if(statementCount >= 0){
            numberOfTransactions = statementCount;
            return true;
        }
        else if(this.test == true){
            System.out.println("Couldn't initialize number of transactions in bank statement with value: "
            + statementCount);
            return false;
        }
        else return false;
    }

    private boolean setNameOfBank(String nameToSet) {
        if(nameToSet != null){
            nameOfBank = nameToSet;
            return true;
        }
        else if(this.test == true){
            System.out.println("Couldn't initialize name of bank with value: "
            + nameToSet);
            return false;
        }
        else return false;
    }

    private boolean setStreetAddress(String addressToSet) {
        if(addressToSet != null){
            streetAddressOfBank = addressToSet;
            return true;
        }
        else if(this.test == true){
            System.out.println("Couldn't initialize street address of bank statement with value: "
            + addressToSet);
            return false;
        }
        else return false;
    }

    private boolean setPhoneNumber(String phoneNumberToSet) {
        if(phoneNumberToSet != null){
            phoneNumberOfBank = phoneNumberToSet;
            return true;
        }
        else if(this.test == true){
            System.out.println("Couldn't initialize phone number of the bank statement with value: "
            + phoneNumberToSet);
            return false;
        }
        else return false;
    }

    private boolean setContentOfStatement(DynamicArray statementContents) {
        if(statementContents != null){
            contentOfStatement = statementContents;
            return true;
        }
        else if (this.test == true){
            System.out.println("Couldn't initialize contents of bank statement with value: "
            + statementContents);
            return false;
        }
        else return false;
    }
    
    private boolean setTotalAmountOfWithdrawals(int amountToSet) {
        if(amountToSet >= 0){
            totalWithdrawals = amountToSet;
            return true;
        }
        else if(this.test == true){
            System.out.println("Couldn't set total amount of withdrawals in bank statement with value: "
            + amountToSet);
            return false;
        }
        else return false;
    }

    private boolean setTotalAmountOfDeposits(int amountToSet) {
        if(amountToSet >= 0){
            totalDeposits = amountToSet;
            return true;
        }
        else if(this.test == true){
            System.out.println("Couldn't initialize total amount of deposits in bank statement with value: "
            + amountToSet);
            return false;
        }
        else return false;
    }
    
    private boolean setValueOfTransactions(){
        valueOfTransactions = getTotalAmountOfWithdrawals() + getTotalAmountOfDeposits();
        return true;
    }
    
    private boolean setOwnerOfBankAccount(String nameToBeSet) {
        if(nameToBeSet != null){
            ownerOfBankAccount = nameToBeSet;
            return true;
        }
        else if (this.test == true){
            System.out.println("Couldn't initialize owner of bank account with value: "
            + nameToBeSet);
            return false;
        }
        else return false;
    }
        
    public String getFirstDate(){
        int dayOfYear = firstDateOfStatement.get(Calendar.DAY_OF_MONTH);
        int monthOfYear = firstDateOfStatement.get(Calendar.MONTH) + 1;
        int valueOfYear = firstDateOfStatement.get(Calendar.YEAR);
        return dayOfYear + "." + monthOfYear + "." + valueOfYear;
    }
    
    public String getLastDate(){
        int dayOfYear = lastDateOfStatement.get(Calendar.DAY_OF_MONTH);
        int monthOfYear = lastDateOfStatement.get(Calendar.MONTH) + 1;
        int valueOfYear = lastDateOfStatement.get(Calendar.YEAR);
        return dayOfYear + "." + monthOfYear + "." + valueOfYear;
    }
    
    public int getNumberOfTransactions(){
        return numberOfTransactions;
    }
    
    public String getNameOfStatement(){
        return nameOfStatement;
    }
    
    public String getCharSet(){
        return charSetOfStatement;
    }
    
    public String getMediaTypeOfStatement(){
        return mediaTypeOfStatement;
    }
    
    public String getNameOfBank(){
        return nameOfBank;
    }
    
    public String getStreetAddressOfBank(){
        return streetAddressOfBank;
    }
    
    public String getOwnerOfBankAccount(){
        return ownerOfBankAccount;
    }
    
    public String getPhoneNumberOfBank(){
        return phoneNumberOfBank;
    }
    
    public DynamicArray getContentOfStatement(){
        return contentOfStatement;
    }

    public int getTotalAmountOfWithdrawals() {
        return totalWithdrawals;
    }
    
    public int getTotalAmountOfDeposits() {
        return totalDeposits;
    }
    
    public int getValueOfTransactions(){
        return valueOfTransactions;
    }

    @Override
    public String toString(){
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

}
