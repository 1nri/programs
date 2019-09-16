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
 */
public class JavaProgramInput 
{
    
    /**
     * DynamicArray is what we use as means of storage.
     */
    private static DynamicArray fileContent;

    /**
     *
     */
    public static void useInput()
    {
        String fname;
        
        // this is a test file, replace this value if some other file is needed
        fname = "/Users/henrijuvonen/Documents/omat_roskat/banking_proj/engine-1.html";
        
        // Testing some of the logic, namely extraction of filename
        System.out.println("Starting program, received the following"
                + " file: " + fname);
        
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
                
                /*
                // testing character extraction
                for(int i = 0; i < line.length(); i++){
                    // variable for the character comparison in order to avoid printing all chars
                    Character toComp;
                    toComp = line.charAt(i);
                    System.out.print(line.charAt(i));
                }
                */
                fileContent.push(line);
                /*
                System.out.println();
                System.out.println("In comparison, this is what the line should look like:");
                
                
                System.out.println(line);
                System.out.println();
                lineCounter++;
                */
            }
            
            /* always close the file after use */
            bufferedReader.close();
            JavaProgramOutput.printStatement(fileContent);
            fileContent = null;
        }
        
        catch(IOException ex)
        {
            System.out.println("Error reading file named '" + fname + "'");
        }
    }
}
