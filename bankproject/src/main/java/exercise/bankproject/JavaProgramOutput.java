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
 */
public class JavaProgramOutput {

    /**
     *
     * @param input
     */
    public static void printStatement(DynamicArray input)
    {
        String fname;
        Scanner scan = new Scanner(System.in);
        fname = "/Users/henrijuvonen/Documents/omat_roskat/banking_proj/javaoutputtest.txt";
        // enter the name of file to create a file and write some content inside
        
        /*
        System.out.print("Enter File Name with Extension (like demo.txt) : ");
        fname = scan.nextLine();
        */
        
        try
        {
            // assume the default encoding
            FileWriter fileWriter = new FileWriter(fname);
            
            // always wrap FileWriter in BufferedWriter
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            // Note that write() method does not automatically
            // append a newline character
            
            int len;
            //System.out.print("How many Sentence/Line you Want to Enter ? ");
            //len = scan.nextInt();
            len = input.arraySize();
            int i;
            String sentence;
			
            //System.out.print("Enter " +len+ " Lines of Sentences followed by Enter : ");
            for(i=0; i<len; i++)
            {
                //sentence = scan.nextLine();
                Object test = input.slide();
                if(test != null)
                {
                    sentence = test.toString();
                    bufferedWriter.write(sentence);
                    bufferedWriter.newLine();
                }
            }
            
            // always close the file
            bufferedWriter.close();
            input = null;
        }
        catch(IOException ex)
        {
            System.out.println("Error writing to file named '" +fname+ "' ..!!");
        }
    }
}
