package Queue;

/**
 * QueueDialog class to manually test the previously programmed Queue classes.
 * Author: Nico Welsch, Oliver Theobald
 * Version: 1.0
 */
import java.util.Scanner;
import java.util.InputMismatchException;
public class QueueDialog{
    // CLASS ATTRIBUTES
    private int index;
    // OBJECTS
    private Queue   queue;
    private Object  o;
    private Scanner input;
    private Scanner option;

    // CLASS CONSTANTS
    private static final int    PRINT          = 1;
    private static final int    APPEND         = 2;
    private static final int    REMOVE         = 3;
    private static final int    CHECK_INDEX    = 4;
    private static final int    CHECK_EMPTY    = 5;
    private static final int    CHECK_FULL     = 6;
    private static final int    CHECK_SIZE     = 7;
    private static final int    ITERATOR       = 8;
    private static final int    END            = 0;

    private static final String ANSI_RESET     = "\u001B[0m";
    private static final String ANSI_RED       = "\u001B[31m";
    private static final String ANSI_PURPLE    = "\u001B[35m";
    private static final String ANSI_CYAN      = "\u001B[36m";

    private static final String INVALID_INPUT  =                                "\n" +
                                                 ANSI_RED   + "INVALID INPUT" +
                                                 ANSI_RESET +                   "\n" ;

    private static final String REMOVED        =                                  "\n" +
                                                 ANSI_RED  + "REMOVED ELEMENT:" + "\n" +
                                                 ANSI_RESET                            ;

    private static final String INDEX_INPUT    =                                      "\n" +
                                                 ANSI_CYAN  + "INPUT AN INDEX SLOT" + "\n" +
                                                 ANSI_RESET                                ;

    private static final String STRING_INPUT   =                                      "\n" +
                                                 ANSI_CYAN  + "INPUT A STRING     " + "\n" +
                                                 ANSI_RESET                                ;

    private static final String NAME_INPUT     =                                      "\n" +
                                                 ANSI_CYAN  + "INPUT A NAME       " + "\n" +
                                                 ANSI_RESET                                ;

    private static final String SURNAME_INPUT  =                                      "\n" +
                                                 ANSI_CYAN  + "INPUT A SURNAME    " + "\n" +
                                                 ANSI_RESET                                ;

    private static final String EMPTY          =                                      "\n" +
                                                 ANSI_CYAN  + "QUEUE IS EMPTY IS  " + "\n" +
                                                 ANSI_RESET                                ;

    private static final String FULL           =                                      "\n" +
                                                 ANSI_CYAN  + "QUEUE IS FULL IS   " + "\n" +
                                                 ANSI_RESET                                ;

    private static final String SIZE           =                                      "\n" +
                                                 ANSI_CYAN  + "CURRENT QUEUE SIZE " + "\n" +
                                                 ANSI_RESET                                ;


    private static final String TERMINATED     =                           "\n" +
                                                 ANSI_RED + "TERMINATED" + "\n" ;


    // MENU LAYOUTS
    private static final String QUEUE_TYPE     =                                            "\n" +
                                                               "------------------------" + "\n" +
                                                                                            "\n" +
                                                 ANSI_PURPLE + "CHOOSE AN OPTION        " + "\n" +
                                                 ANSI_RESET  +                              "\n" +
                                                               "1: STRING               " + "\n" +
                                                               "2: PERSON               " + "\n" +
                                                                                            "\n" +
                                                               "------------------------" + "\n" +
                                                                                            "\n" +
                                                               "->         "              ;

    private static final String MAIN_MENU      =                                                               "\n" +
                                                                                  "------------------------" + "\n" +
                                                                                                               "\n" +
                                                 PRINT       +                    ": PRINT QUEUE           " + "\n" +
                                                 APPEND      +                    ": APPEND TO   QUEUE     " + "\n" +
                                                 REMOVE      +                    ": REMOVE FROM QUEUE     " + "\n" +
                                                 CHECK_INDEX +                    ": CHECK INDEX OF OBJECT " + "\n" +
                                                 CHECK_EMPTY +                    ": CHECK IF EMPTY        " + "\n" +
                                                 CHECK_FULL  +                    ": CHECK IF FULL         " + "\n" +
                                                 CHECK_SIZE  +                    ": CHECK CURRENT SIZE    " + "\n" +
                                                 ITERATOR    +                    ": ITERATOR MENU         " + "\n" +
                                                 END         + ":" + ANSI_RED   +  " END                   " + "\n" +
                                                                     ANSI_RESET +                              "\n" +
                                                                                  "------------------------" + "\n" +
                                                                                                               "\n" +
                                                                                  "->         "              ;

    private static final String ITERATOR_MENU  =                                            "\n" +
                                                               "------------------------" + "\n" +
                                                                                            "\n" +
                                                 ANSI_PURPLE + "CHOOSE AN OPTION        " + "\n" +
                                                 ANSI_RESET  +                              "\n" +
                                                               "1: SMALLEST SURNAME     " + "\n" +
                                                               "2: PRINT QUEUE          " + "\n" +
                                                                                            "\n" +
                                                               "------------------------" + "\n" +
                                                                                            "\n" +
                                                               "->         "              ;

    /**
     * constructor in which objects are created.
     */
    public QueueDialog(){
        input  = new Scanner(System.in);
        option = new Scanner(System.in);
    }
     /**
     * Main loop for the dialogue-programm which trys to execute the user
     * interface and the assigned functions as long as the programme
     * doesn't catch an exception.
     * If it catches an exception the programm will print out the exception
     * so that the user can see what went wrong.
     */
    public void start(){
        queueMenu();
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
        System.out.print(MAIN_MENU);
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
     */
    private void ausfuehrenFunktion(int funktion){
        if(funktion == PRINT){
            System.out.println(queue);
        } else if(funktion == APPEND){
            queue.addLast(typeMenu());
        } else if(funktion == REMOVE){
            o = queue.removeFirst();
            System.out.println(REMOVED + o + "\n");
        } else if(funktion == CHECK_INDEX){
            index = InputFunctions.readInt(input, INDEX_INPUT);
            System.out.println(ANSI_CYAN  + "INDEX: " +
                               ANSI_RESET + index + "\n"
                              );
            queue.get(index);
        } else if(funktion == CHECK_EMPTY){
            System.out.println(EMPTY);
            queue.empty();
        } else if(funktion == CHECK_FULL){
            System.out.println(FULL);
            queue.full();
        } else if(funktion == CHECK_SIZE){
            System.out.println(SIZE);
            queue.size();
        } else if(funktion == ITERATOR){
            iteratorMenu();
        } else if(funktion == END){
            System.out.println(TERMINATED);
        } else{
            System.out.println(INVALID_INPUT);
        }
    }

    // ################################## MENUS ###################################

    /**
     * Method which lets the user choose which kind of queue to create.
     */
    private void queueMenu(){
        // MENU TO CHOOSE A QUEUE TO WORK WITH
        System.out.println(QUEUE_TYPE);
        String userInput = option.next();
        // STRING QUEUE
        if(userInput.equals("1")){
            queue = new StringQueue(5);
        // PERSON QUEUE
        } else if(userInput.equals("2")){
            queue = new PersonQueue(5);
        // INVALID
        } else{
            System.out.println(INVALID_INPUT);
        }
    }

    /**
     * Method which lets the user choose which kind of object to create.
     * @return newly created object
     */
    private Object typeMenu(){
        Object newObject = null;
        String name;
        String vorname;
        // MENU TO CHOOSE A QUEUE TYPE
        System.out.println(QUEUE_TYPE);
        String userInput = option.next();
        // STRING QUEUE
        if(userInput.equals("1")){
            newObject = new String(InputFunctions.readString(input, STRING_INPUT));
        // PERSON QUEUE
        } else if(userInput.equals("2")){
            name      = InputFunctions.readString(input, NAME_INPUT);
            vorname   = InputFunctions.readString(input, SURNAME_INPUT);
            newObject = new Person(name, vorname);
        // INVALID
        } else{
            System.out.println(INVALID_INPUT);
        }
        return newObject;
    }

    /**
     * Method which lets the user choose which kind of queue to create.
     */
    private void iteratorMenu(){
        // MENU TO CHOOSE A QUEUE TO WORK WITH
        System.out.println(ITERATOR_MENU);
        String userInput = option.next();
        // STRING QUEUE
        if(userInput.equals("1")){
            PersonQueue pq = (PersonQueue) queue;
            pq.smallest();
        // PERSON QUEUE
        } else if(userInput.equals("2")){
            PersonQueue pq = (PersonQueue) queue;
            pq.smallest();
        // INVALID
        } else{
            System.out.println(INVALID_INPUT);
        }
    }

    // ############################################################################

    /**
     * Main method to create the dialogue-object.
     */
    public static void main (String[]args){
       new QueueDialog().start();
    }
}
