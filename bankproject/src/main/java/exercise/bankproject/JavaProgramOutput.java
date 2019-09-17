/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise.bankproject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author henrijuvonen
 * 
 * This is copied and modified version of the program example at 
 * https://codescracker.com/java/program/java-program-write-to-file.htm
 * 
 * edited 16.9.2019
 * reformatting
 * edited 17.9.2019
 * replaced Object with ItemOfDynamicArray as per the current implementation 
 * of DynamicArray
 */
public class JavaProgramOutput 
{

    /**
     * this is used to print out the stored bank statement, useful for a lot of 
     * stuff in addition to testing
     * @param input
     * @param t
     */
    public static void printStatement(DynamicArray input, boolean t)
    {
        String fname;
        Scanner scan = new Scanner(System.in);
        fname = "/Users/henrijuvonen/Documents/omat_roskat/banking_proj/javaoutputtest.txt";
        
        try
        {
            
            // assume the default encoding
            FileWriter fileWriter = new FileWriter(fname);
            
            // always wrap FileWriter in BufferedWriter
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int len;
            len = input.arraySize();
            int i;
            String sentence;
			
            //System.out.print("Enter " +len+ " Lines of Sentences followed by Enter : ");
            for(i=0; i<len; i++)
            {
                
                ItemOfDynamicArray test = input.peek(i);
                if(test != null)
                {
                    sentence = test.toString(true);
                    bufferedWriter.write(sentence);
                    bufferedWriter.newLine();
                }
            }
            
            // always close the file
            bufferedWriter.close();
            
            if(t==true)
            {
                input = null;
            }
            
        }
        
        catch(IOException ex)
        {
            System.out.println("Error writing to file named '" +fname+ "' ..!!");
        }
    }
}
