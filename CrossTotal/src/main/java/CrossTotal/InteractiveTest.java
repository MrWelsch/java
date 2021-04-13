package CrossTotal;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.ArrayDeque;

/**
 * Interactive Test for the CrossTotal project.
 * @author Nico Welsch, Oliver Theobald
 * @version 1.0
 */
public class InteractiveTest {

    private Collection<Integer> collection;
    private Consumer consumer;
    private Scanner input;

    // class constants
    private static final int ADD                            = 1;
    private static final int CONSUME                        = 2;
    private static final int NUMBER_OF_DIFFERENT_RESULTS    = 3;
    private static final int NUMBER_OF_OCCURANCES           = 4;
    private static final int GET_ASCENDING                  = 5;
    private static final int GET_DESCENDING                 = 6;
    private static final int GET_TIMESTAMPS                 = 7;
    private static final int EXECUTE_LOOP                   = 8;
    private static final int END                            = 0;

    private static final String ONE = "1";
    private static final String TWO = "2";

    private static final String MENU =                                                                 "\n" +
                                                                      "----------------------------" + "\n" +
                                        ADD                         + ": ADD                       " + "\n" +
                                        CONSUME                     + ": CONSUME                   " + "\n" +
                                        NUMBER_OF_DIFFERENT_RESULTS + ": DIFFERENT RESULTS (COUNT) " + "\n" +
                                        NUMBER_OF_OCCURANCES        + ": OCCURANCES (COUNT)        " + "\n" +
                                        GET_ASCENDING               + ": ASCENDING ORDER           " + "\n" +
                                        GET_DESCENDING              + ": DESCENDING ORDER          " + "\n" +
                                        GET_TIMESTAMPS              + ": TIMESTAMPS                " + "\n" +
                                        EXECUTE_LOOP                + ": LOOP                      " + "\n" +
                                        END                         + ": END                       " + "\n" +
                                                                      "----------------------------" + "\n" +
                                                                      "->            "               ;

    private static final String DATA_STRUCTURE =                                       "\n" +
                                                      "----------------------------" + "\n" +
                                                      "CHOOSE AN ORDER             " + "\n" +
                                                                                       "\n" +
                                        ONE         + ": FIFO                      " + "\n" +
                                        TWO         + ": NATURAL ORDER             " + "\n" +
                                                      "----------------------------" + "\n" +
                                                      "->            "               ;

    public InteractiveTest() {
        consumer = new Consumer();
        input = new Scanner(System.in);
    }

    /**
     * Main loop for the interactive test which trys to execute the user interface
     * and the assigned functions as long as the program doesn't catch an
     * exception. If it catches an exception the program will print out the
     * exception so that the user can see what went wrong.
     */
    public void start() {
        chooseDataStructure();
        int function = -1;
        while (function != END) {
            try {
                function = readFunction();
                executeFunction(function);
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            } catch (InputMismatchException e) {
                System.out.println(e);
                input.nextLine();
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }

    /**
     * Asks which kind of collection should be used to store integer values.
     */
    private void chooseDataStructure() {
        System.out.print(DATA_STRUCTURE);
        String userInput = input.next();
        if (userInput.equals(ONE)) {
            collection = new ArrayDeque<>();
        } else if (userInput.equals(TWO)) {
            collection = new TreeSet<>();
        } else {
            throw new InputMismatchException();
        }
    }

    /**
     * Creates an user interface which is able to read the user input and
     * executes the assigned function.
     */
    private int readFunction() {
        int function;
        System.out.print(MENU);
        function = input.nextInt();
        input.nextLine();
        return function;
    }

    /**
     * Assigns each expression a method which runs if the function is
     * triggered. If none of the functions is triggered the program will print
     * out: "False Function". After executing the assigned method the program will
     * then print out the object.
     */
    private void executeFunction(int function) {
        if (function == ADD) {
            collection.add(Producer.produce());
            System.out.println("\n" +  "COLLECTION" + "\n" + collection);
        } else if (function == CONSUME) {
            if(collection instanceof ArrayDeque){
                consumer.consume(((ArrayDeque<Integer>)collection).poll());
            } else {
                consumer.consume(((TreeSet<Integer>)collection).pollFirst());
            }
            System.out.println("\n" +  "MAP" + "\n" + consumer);
        } else if (function == NUMBER_OF_DIFFERENT_RESULTS) {
            System.out.println("\n" +  "NUMBER OF DIFFERENT RESULTS");
            System.out.println(consumer.numberOfDifferentResults());
        } else if (function == NUMBER_OF_OCCURANCES) {
            System.out.println("\n" + "NUMBER OF OCCURENCES");
            System.out.println(consumer.numberOfOccurrences(readInt()));
        } else if (function == GET_ASCENDING) {
            System.out.println("\n" +  "CROSS-TOTALS");
            System.out.println(consumer.getCrossTotalsAscending());
        } else if (function == GET_DESCENDING) {
            System.out.println("\n" + "CROSS-TOTALS");
            System.out.println(consumer.getCrossTotalsDescending());
        } else if (function == GET_TIMESTAMPS) {
            System.out.println("\n" + "TIMESTAMPS");
            System.out.println(consumer.getTimestampsForResult(readInt()));
        } else if (function == EXECUTE_LOOP) {
            executeRandomly();
            System.out.println(consumer);   // TESTING
        } else if (function == END) {
            System.out.println("\n" + "TERMINATED" + "\n");
        } else {
            System.out.println();
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     */
    private void executeRandomly(){
        Random random = new Random();
        for(int i = 0; i < 10000; i++){
            if(random.nextInt(1001) > 0){
                collection.add(Producer.produce());
            } else{
                if(collection instanceof ArrayDeque){
                    consumer.consume(((ArrayDeque<Integer>)collection).poll());
                } else {
                    consumer.consume(((TreeSet<Integer>)collection).pollFirst());
                }
            }
        }
    }

    /**
     * Returns an input integer value.
     * @return integer
     */
    private int readInt(){
        System.out.println("ENTER AN INTEGER");
        return input.nextInt();
    }

    /**
     * Main method to create the dialogue-object.
     */
    public static void main(String[] args) {
        new InteractiveTest().start();
    }
}
