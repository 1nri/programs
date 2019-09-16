/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise.bankproject;

/**
 *
 * @author henrijuvonen
 * 
 * i guess this was initiated on 1.9.2019...
 * 
 * edited on 16.9.2019
 * continued on the lost thought to get this reading xml formatted bank 
 * statements from Aktia.
 * 
 */
public class StatementParser 
{
    
    private static DynamicArray singleStatement;
    private static DynamicArray transactions;
    private static DynamicArray statements;
    
    /**
     * migrates the content of the bank statement file based DynamicArray into
     * separate dynamic arrays for all statements, a single statement and 
     * transactions
     * @param file 
     * @param t 
     */
    public static void parser(DynamicArray file, boolean t) 
    {
        
        String sentence;
        statements = file;
        
        for(int i=0; i<file.arraySize(); i++)
        {
            /**
             * this was the first idea, to just read the file line by line
             * sentence = scan.nextLine();
             */
            Object test = file.slide();
            
            /**
             * not null items can be interpreted char by char or word by word
             */
            if(test != null)
            {
                sentence = test.toString();
                /**
                 * this was the first idea, to read the file line by line
                 * bufferedWriter.write(sentence);
                 * bufferedWriter.newLine();
                 */
                
                /**
                 * now what we're about to do is to define that we've reached here
                 */
                if(t==true)
                {
                    System.out.println(test);
                }
            }    
        }
    }
    
    private void InspectSentence(String s)
    {
        switch(s){
            case "title":
                
        }
                
    }
}
