/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise.bankproject;

/**
 *
 * @author henrijuvonen
 */
public class StatementParser {
    
    private DynamicArray singleStatement;
    private DynamicArray transactions;
    private DynamicArray statements;
    
    public void StatementParser(DynamicArray file) {
        
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
    private void InspectSentence(String s){
        
    }
}
