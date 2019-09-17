/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise.bankproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author henrijuvonen
 * 
 * This is copied and modified version of the program example at 
 * https://codescracker.com/java/program/java-program-read-and-display-file.htm
 * 
 * edited 16.9.2019
 * reformatting, commenting
 * added alternate use of input where a DynamicArray is returned back to the 
 * calling module
 * 
 * edited 17.9.2019
 * further improvements, added trim()
 */
public class JavaProgramInput 
{
    
    /**
     * DynamicArray is what we use as means of storage.
     */
    private static DynamicArray fileContent;

    /**
     * the main logic, this reads the input and tries to extract important parts
     * of information from it
     * @param name
     * @param t
     */
    public static void useInput(String name, boolean t)
    {
        String fname = name;
        
        // Testing some of the logic, namely extraction of filename
        if(t==true)
        {
            System.out.println("Starting program, received the following"
                + " file: " + fname);
        }
        
        Scanner scan = new Scanner(System.in);
        
        /* enter filename with extension to open and read its content */
        /* the following two lines of logic are not needed in this test */
        //System.out.print("Enter File Name to Open (with extension like file.txt) : ");        
        //fname = scan.nextLine();
        
        /* this will reference only one line at a time */
        fileContent = new DynamicArray();
        String line = null;
        
        try
        {
            /* FileReader reads text files in the default encoding */
            FileReader fileReader = new FileReader(fname);
            
            /* always wrap the FileReader in BufferedReader */
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int lineCounter = 0;
            
            while((line = bufferedReader.readLine()) != null)
            {
                // you might want to try this at some point:
                line = line.replaceAll("&nbsp;"," ");
                line = line.trim();
                fileContent.push(line);
            }
            
            /* always close the file after use */
            bufferedReader.close();
            JavaProgramOutput.printStatement(fileContent, t);
            fileContent = null;
        }
        
        catch(IOException ex)
        {
            System.out.println("Error reading file named '" + fname + "'");
        }
    }
    
    /**
     * alternative functionality, useful when not testing
     * @param name
     * @param t
     * @param a
     * @return 
     */
    public static DynamicArray useInput(String name, boolean t, DynamicArray a)
    {
        String fname = name;
        
        // Testing some of the logic, namely extraction of filename
        if(t==true)
        {
            System.out.println("Testing alternate use of input.");
            System.out.println("Starting program, received the following"
                + " file: " + fname);
        }
        
        Scanner scan = new Scanner(System.in);
        
        /* this will reference only one line at a time */
        fileContent = a;
        
        try
        {
            /* FileReader reads text files in the default encoding */
            FileReader fileReader = new FileReader(fname);
            
            try ( /* always wrap the FileReader in BufferedReader */ BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                int lineCounter = 0;
                String line = bufferedReader.readLine();
                
                do {
                    
                    /**
                     * this will make the line a little more pretty
                     */
                    line = line.replaceAll("&nbsp;"," ");
                    line = line.trim();
                    
                    /**
                     * this will further reduce blank spaces along the stored
                     * lines of xml
                     */
                    if(!line.equals(" ") && !line.isBlank())
                    {
                        fileContent.push(line);
                    }
                    
                    line = bufferedReader.readLine();
                } while (line != null);
                
            }
            
            /* always close the file after use */
            fileReader.close();
            
            if(t==true)
            {
                JavaProgramOutput.printStatement(fileContent, t);
            }
            
        }
        
        catch(IOException ex)
        {
            System.out.println("Error reading file named '" + fname + "'");
            return null;
        }
        
        if(t==true){
            System.out.println("Testing use of input has completed.");
        }
        
        return fileContent;
    }
}
