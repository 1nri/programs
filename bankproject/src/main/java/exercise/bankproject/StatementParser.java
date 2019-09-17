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
 * edited 17.9.2019
 * started on the sentence inspector
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
            System.out.println("here we are starting to inspect soon");
            ItemOfDynamicArray test = file.slide();
            
            /**
             * not null items can be interpreted char by char or word by word
             */
            if(test != null)
            {
                System.out.println("calling the inspector, item was not null");
                inspectSentence(test.toString(), true);
                sentence = test.toString(true);
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
    
    private static void inspectSentence(String s, boolean t)
    {
        
        boolean pleaseContinue = false;
        if(t==true)
        {
            System.out.println("testing sentence inspector");
        }
        
        String test = "";

        //while( | i < test.length()-1)
        for(int i = 0; i < s.length(); i++)
        {
            
            if(s.charAt(i) == '<')
            {
                test = test + s.charAt(i);
                pleaseContinue = true;
            }
            
            else if(pleaseContinue)
            {
                test = test + s.charAt(i);
            }
            
            if(test.equals("<?"))
            {
                System.out.println("this line contains metadata, skip");
                break;
            }
            
            if(test.equals("<!"))
            {
                System.out.println("this line contains metadata, skip");
                break;
            }
            
            if(pleaseContinue && s.charAt(i) == '>')
            {
                break;
            }
        }
        
        System.out.println("sentence inspected, result is : " + test);
        
        switch(test)
        {
            case "<title>":
                System.out.println("this is the title :  " + s);
                break;
            case "<div>":
                System.out.println("this is divisor : " + s);
                break;
            case "<br>":
                System.out.println("this is a line break : " + s);
                break;
            case "<head>":
                System.out.println("this is header : " + s);
                break;
        }
    }
}
