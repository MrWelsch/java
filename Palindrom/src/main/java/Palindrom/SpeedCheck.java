package Palindrom;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * SpeedCheck class which saves the speeds of the two approaches to check for
 * palindrome in specified files which are later used in a plot program to
 * showcase the difference between recursive and iterative..
 * Palindroms are words which are identical no matter which way they are read.
 * Author: Nico Welsch, Oliver Theobald
 * Version: 1.0
 */
public class SpeedCheck {
    File testFile;
    Scanner input;

    PalindromRekursiv pr;
    PalindromIterativ pi;

    FileWriter writeIterative;
    FileWriter writeRecursive;
    FileWriter test;
    /**
     * Constructor to create objects.
     */
    public SpeedCheck() throws IOException{
        testFile       = new File("Test");
        input          = new Scanner(testFile);

        pi             = new PalindromIterativ();
        pr             = new PalindromRekursiv();

        writeIterative = new FileWriter("SpeedCheckIterative.dat", true); // true = append
        writeRecursive = new FileWriter("SpeedCheckRecursive.dat", true);
        test           = new FileWriter("Test", true);
    }
    /**
     * Method to execute a SpeedCheck for the iterative method and save it under
     * the specified filename.
     * @throws IOException
     */
    public void speedCheckIterative() throws IOException {
        while(input.hasNext()){
            String wort  = input.next();
            long start = System.nanoTime();
            pi.istPalindrom(wort);
            long end = System.nanoTime();
            long time = end - start;
            String format = (wort.length() - 1) + "\t\t\t\t\t\t\t\t" + time + "\n";
            writeIterative.write(format);
        }
        writeIterative.close();
    }
    /**
     * Method to execute a SpeedCheck for the recursive method and save it under
     * the specified filename.
     * @throws IOException
     */
    public void speedCheckRecursive() throws IOException{
        while(input.hasNext()){
            String wort = input.next();
            long start = System.nanoTime();
            pr.istPalindrom(wort);
            long end = System.nanoTime();
            long time = end - start;
            String format = (wort.length() - 1) + "\t\t\t\t\t\t\t\t" + time + "\n";
            writeRecursive.write(format);
        }
        writeRecursive.close();
    }
    /**
     * Method to create a file which is used in the SpeedChecks above as a
     * input.
     * @throws IOException
     */
    public void createFile() throws IOException{
        String wort = "";
        int i = 1;
        while(i < 5000){
            wort += "aa";
            i++;
            if(wort.length() >= i) {
                wort += "\n";
                test.write(wort);
                wort = "";
            }
        }
        test.close();
    }
}

