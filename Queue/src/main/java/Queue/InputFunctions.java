package Queue;

// IMPORTS
import java.util.Scanner;

/**
 * Class with a collection of methods to realize various inputs.
 * @author Nico Welsch, Oliver Theobald
 * @version 1.0
 */
public class InputFunctions{

    /**
     * Standard constructor to prevent the creation of a object, which isn't
     * needed in this case.
     */
    private InputFunctions(){
    }

    /**
     * Method to give out a prompt and reads the first integer from an input String.
     * All following chars in that line will be ignored.
     * @param input input to read
     * @param prompt prompt text to ask for input
     * @return input int number
     */
    public static int readInt(Scanner input, String prompt){
        System.out.print(prompt);
        int number = input.nextInt();
        input.nextLine();
        return number;
    }

    /**
     * Method to give out a prompt and reads the first long from an input String.
     * All following chars in that line will be ignored.
     * @param input input to read
     * @param prompt prompt text to ask for input
     * @return input long number
     */
    public static long readLong(Scanner input, String prompt){
        System.out.print(prompt);
        long number = input.nextLong();
        input.nextLine();
        return number;
    }

    /**
     * Method to give out a prompt and reads the first float from an input String.
     * All following chars in that line will be ignored.
     * @param input input to read
     * @param prompt prompt text to ask for input
     * @return input float number
     */
    public static float readFloat(Scanner input, String prompt){
        System.out.print(prompt);
        float number = input.nextFloat();
        input.nextLine();
        return number;
    }

    /**
     * Method to give out a prompt and reads the first double from an input String.
     * All following chars in that line will be ignored.
     * @param input input to read
     * @param prompt prompt text to ask for input
     * @return input double number
     */
    public static double readDouble(Scanner input, String prompt){
        System.out.print(prompt);
        double number = input.nextDouble();
        input.nextLine();
        return number;
    }

    /**
     * Method to give out a prompt and reads the whole line as a input String.
     * @param input input to read
     * @param prompt prompt text to ask for input
     * @return input String = whole line
     */
    public static String readString(Scanner input, String prompt){
        System.out.print(prompt);
        String line = input.nextLine();
        return line;
    }

    /**
     * Method to read the whole line as a String.
     * @param input input to read
     * @return input String = whole line or null if there is no more line.
     */
    public static String readString(Scanner input){
        String line = null;
        if(input.hasNextLine()){
            line = input.nextLine();
        }
        return line;
    }

    /**
     * Method to give out a prompt and reads the first char from an input String.
     * All following chars in that line will be ignored.
     * @param input input to read
     * @param prompt prompt text to ask for input
     * @return input char
     */
    public static char readChar(Scanner input, String prompt){
        System.out.print(prompt);
        String line = input.nextLine();
        char character = line.toCharArray()[0];
        return character;
    }
}
