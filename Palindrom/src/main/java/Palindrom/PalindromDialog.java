package Palindrom;

/**
 * PalindromDialog class to manually test the previously programmed Palindrome classes.
 * Author: Nico Welsch, Oliver Theobald
 * Version: 1.0
 */
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
public class PalindromDialog{
    // objects
    private PalindromIterativ pi;
    private PalindromRekursiv pr;
    private Scanner input;
    private Scanner option;
    private File file;

    // class constants
    private static final int PALINDROME_CHECK  = 1;
    private static final int SPEEDTEST         = 2;
    private static final int END               = 0;

    private static final String TRUE     = "It is a palindrome";
    private static final String FALSE    = "It is a not palindrome";
    private static final String FILENAME = "test.txt";

    /**
     * constructor in which objects are created.
     */
    public PalindromDialog(){
        input  = new Scanner(System.in);
        option = new Scanner(System.in);
        pi     = new PalindromIterativ();
        pr     = new PalindromRekursiv();
        // specify filepath to the file
        file  = new File(FILENAME);
    }
     /**
     * Main loop for the dialogue-programm which trys to execute the user
     * interface and the assigned functions as long as the programme
     * doesn't catch an exception.
     * If it catches an exception the programm will print out the exception
     * so that the user can see what went wrong.
     */
    public void start(){
        int funktion = -1;
        while(funktion != END){
            try{
                funktion = einleseFunktion();
                ausfuehrenFunktion(funktion);
            } catch(IllegalArgumentException e){
                System.out.println(e);
            } catch(InputMismatchException e){
                System.out.println(e);
                input.nextLine();
            } catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
    }
    /**
     * Method to create an user interface which is able to read the user input
     * and execute the assigned function.
     */
    private int einleseFunktion(){
        int funktion;
        System.out.print(                        "##########"   + "\n" +
                         PALINDROME_CHECK      + ":check "      + "\n" +
                         SPEEDTEST             + ":speedtest "  + "\n" +
                         END                   + ":end "        + "\n" +
                                                 "##########"   + "\n" +
                                                 "-> "
        );
        funktion = input.nextInt();
        input.nextLine();
        return funktion;
    }
    /**
     * Method to assign each expression a method which runs if the function is
     * triggered.
     * If none of the functions are triggered the programm will print out:
     * "False Function".
     * After executing the assigned method the programm will then print out
     * the object.i
     * @throws IOException
     */
    private void ausfuehrenFunktion(int funktion) throws IOException{
        if(funktion == PALINDROME_CHECK){
            inputSelection();
        } else if(funktion == SPEEDTEST){
            new SpeedCheck().createFile();
            new SpeedCheck().speedCheckIterative();
            new SpeedCheck().speedCheckRecursive();
            System.out.println(
                               "Test was created and saved as:" + "\n" +
                               "SpeedCheckIterative.dat"        + "\n" +
                               "SpeedCheckRecursive.dat"
                               );
        } else if(funktion == END){
            System.out.println("Program terminated");
        } else{
            System.out.println("Wrong function");
        }
    }
    /**
     * Method which lets the user choose which method is going to be used to
     * check for palindrome.
     * @throws IOException
     */
    private void inputSelection() throws IOException{
        // give user the option to choose which kind of algorithm he want to use
        System.out.println(
                           "Which algorithm do you want to use?"      + "\n" +
                           "1: Iterative"                             + "\n" +
                           "2: Recursive"                             + "\n"
                           );
        String userInput = option.nextLine();
        // Iterative
        if(userInput.equals("1")){
            // give the user the option to choose the input format of the String
            System.out.println(
                               "Which check do you want to execute?" + "\n" +
                               "1: File"                             + "\n" +
                               "2: Word"                             + "\n"
                               );
            userInput = option.nextLine();
            // File
            if(userInput.equals("1")){
                if(pi.istPalindrom(readFile(file)) == true){
                    System.out.println(TRUE);
                } else{
                    System.out.println(FALSE);
                };
            } else if(userInput.equals("2")){
                // Word
                if(pi.istPalindrom(readString()) == true){
                    System.out.println(TRUE);
                } else{
                    System.out.println(FALSE);
                };
            // Invalid Input
            } else{
                System.out.println("Invalid Input");
            }
        // Recursive
        } else if(userInput.equals("2")){
            // give the user the option to choose the input format of the String
            System.out.println(
                               "Which check do you want to execute?" + "\n" +
                               "1: File"                             + "\n" +
                               "2: Word"                             + "\n"
                               );
            userInput = option.nextLine();
            // File
            if(userInput.equals("1")){
                if(pr.istPalindrom(readFile(file)) == true){
                    System.out.println(TRUE);
                } else{
                    System.out.println(FALSE);
                };
            } else if(userInput.equals("2")){
                // Word
                if(pr.istPalindrom(readString()) == true){
                    System.out.println(TRUE);
                } else{
                    System.out.println(FALSE);
                };
            // Invalid Input
            } else{
                System.out.println("Invalid Input");
            }
        // Invalid Input
        } else{
            System.out.println("Invalid Input");
        }
    }
    /**
     * Method which lets the user enter a String which is used to enter a word
     * as a String.
     * @return entered String
     */
    public String readString(){
        String wort;
        System.out.println("String to be checked: ");
        wort = input.nextLine();
        return wort;
    }
    /**
     * Method to transform the contents of a File to a String.
     * @param file File object
     */
    public String readFile(File file) throws IOException{
        try{
            new String(Files.readAllBytes(Paths.get("test.txt")), StandardCharsets.UTF_8);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }
    /**
     * Main method to create the dialogue-object.
     */
    public static void main (String[]args){
       new PalindromDialog().start();
    }
}
