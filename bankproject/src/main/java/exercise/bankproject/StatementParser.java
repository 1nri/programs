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
    
    private DynamicArray singleStatement;
    private DynamicArray transactions;
    private DynamicArray statements;
    
    public void StatementParser(DynamicArray file) 
    {
        
        String sentence;
        statements = file;
        
        for(int i=0; i<file.arraySize(); i++){
            //sentence = scan.nextLine();
            Object test = file.slide();
            
            if(test != null)
            {
                sentence = test.toString();
                //bufferedWriter.write(sentence);
                //bufferedWriter.newLine();
            }    
        }
    }
    private void InspectSentence(String s)
    {
        
        
    }
}
