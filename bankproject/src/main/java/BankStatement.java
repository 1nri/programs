
import exercise.bankproject.DynamicArray;
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
    
    private Date firstDateOfStatement;
    private Date lastDateOfStatement;
    private String nameOfStatement;
    private String charSetOfStatement;
    private String mediaTypeOfStatement;
    private int numberOfTransactions;
    private String nameOfBank;
    private String streetAddressOfBank;
    private String phoneNumberOfBank;
    private DynamicArray contentOfStatement;
    
    public BankStatement(){
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
    
    public boolean BankStatement(Date a, Date b, String c, String d, String e, int f, String g, String h, String i, DynamicArray j){
        if(setFirstDateOfStatement(a) && setLastDateOfStatement(b) && setNameOfStatement(c) && 
                setCharSet(d) && setMediaType(e) && setNumberOfTransactions(f) && setNameOfBank(g) &&
                setStreetAddress(h) && setPhoneNumber(i) && setContentOfStatement(j)){
            return true;
        }
        else return false;
    }
    
    private boolean setFirstDateOfStatement(Date dateToSet){
        if(dateToSet != null){
            firstDateOfStatement = dateToSet;
            return true;
        }
        else return false;
    }

    private boolean setLastDateOfStatement(Date dateToSet) {
        if(dateToSet != null){
            lastDateOfStatement = dateToSet;
            return true;
        }
        else return false;
    }

    private boolean setNameOfStatement(String nameToSet) {
        if(nameToSet != null){
            nameOfStatement = nameToSet;
            return true;
        }
        else return false;
    }

    private boolean setCharSet(String charSetToSet) {
        if(charSetToSet != null){
            charSetOfStatement = charSetToSet;
            return true;
        }
        else return false;
    }

    private boolean setMediaType(String mediaTypeToSet) {
        if(mediaTypeToSet != null){
            mediaTypeOfStatement = mediaTypeToSet;
            return true;
        }
        else return false;
    }

    private boolean setNumberOfTransactions(int statementCount) {
        if(statementCount >= 0){
            numberOfTransactions = statementCount;
            return true;
        }
        else return false;
    }

    private boolean setNameOfBank(String nameToSet) {
        if(nameToSet != null){
            nameOfBank = nameToSet;
            return true;
        }
        else return false;
    }

    private boolean setStreetAddress(String addressToSet) {
        if(addressToSet != null){
            streetAddressOfBank = addressToSet;
            return true;
        }
        else return false;
    }

    private boolean setPhoneNumber(String phoneNumberToSet) {
        if(phoneNumberToSet != null){
            phoneNumberOfBank = phoneNumberToSet;
            return true;
        }
        else return false;
    }

    private boolean setContentOfStatement(DynamicArray statementContents) {
        if(statementContents != null){
            contentOfStatement = statementContents;
            return true;
        }
        else return false;
    }
    
    public Date getFirstDate(){
        return firstDateOfStatement;
    }
    
    public Date getLastDate(){
        return lastDateOfStatement;
    }
    
    public int getNumberOfTransactions(){
        return numberOfTransactions;
    }
    
    public String getNameOfStatement(){
        return nameOfStatement;
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
    
    
    public String getPhoneNumberOfBank(){
        return phoneNumberOfBank;
    }
    
    public DynamicArray getContentOfStatement(){
        return contentOfStatement;
    }
}
