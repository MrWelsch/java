package NumberCruncher;

/**
 * NumberCruncher class to manually test the previously programmed NumberCruncher classes.
 * Author: Nico Welsch, Oliver Theobald
 * Version: 1.0
 */
import java.util.Scanner;
import java.util.InputMismatchException;
public class NumberCruncherDialog{
    // OBJECTS
    private Scanner                input;
    private Scanner                option;

    private String[] operations;
    private String[] functions;

    private NumberCruncherAnonym   nca;
    private NumberCruncherTopLevel nctl;

    // CLASS CONSTANTS
    private static final int    FUNCTIONS      = 1;
    private static final int    CRUNCH         = 2;
    private static final int    PRINT          = 3;
    private static final int    END            = 0;

    private static final String ANSI_RESET     = "\u001B[0m";
    private static final String ANSI_RED       = "\u001B[31m";
    private static final String ANSI_PURPLE    = "\u001B[35m";

    private static final String INVALID_INPUT  =                                "\n" +
                                                 ANSI_RED   + "INVALID INPUT" +
                                                 ANSI_RESET +                   "\n" ;

    private static final String TERMINATED     =                           "\n" +
                                                 ANSI_RED + "TERMINATED" + "\n" ;

    // MENU LAYOUTS
    private static final String MAIN_MENU      =                                "\n" +
                                                                                "------------------------" + "\n" +
                                                                                                             "\n" +
                                                 FUNCTIONS +                    ": FUNCTIONS             " + "\n" +
                                                 CRUNCH    +                    ": CRUNCH                " + "\n" +
                                                 PRINT     +                    ": PRINT                 " + "\n" +
                                                 END       + ":" + ANSI_RED   +  " END                   " + "\n" +
                                                                   ANSI_RESET +                              "\n" +
                                                                                "------------------------" + "\n" +
                                                                                                             "\n" +
                                                                                "->         "              ;

    private static final String CLASS_MENU     =                                            "\n" +
                                                               "------------------------" + "\n" +
                                                                                            "\n" +
                                                 ANSI_PURPLE + "CHOOSE AN OPTION        " + "\n" +
                                                 ANSI_RESET  +                              "\n" +
                                                               "1: ANONYMOUS            " + "\n" +
                                                               "2: TOPLEVEL             " + "\n" +
                                                                                            "\n" +
                                                               "------------------------" + "\n" +
                                                                                            "\n" +
                                                               "->         "              ;

    private static final String FUNCTIONS_MENU =                                                                "\n" +
                                                                                   "------------------------" + "\n" +
                                                                                                                "\n" +
                                                 ANSI_PURPLE +                     "CHOOSE A FUNCTION       " + "\n" +
                                                 ANSI_RESET  +                                                  "\n" +
                                                                                   "1: SUMMIZE              " + "\n" +
                                                                                   "2: SWIRL                " + "\n" +
                                                                                   "3: DIVIDE               " + "\n" +
                                                                                   "4: SUBTRACT             " + "\n" +
                                                                                   "5: AVERAGE              " + "\n" +
                                                 END         + ":" + ANSI_RED   +    " END                  " + "\n" +
                                                                   ANSI_RESET +                                 "\n" +
                                                                                                                "\n" +
                                                                                   "------------------------" + "\n" +
                                                                                                                "\n" +
                                                                                   "->         "              ;

    /**
     * constructor in which objects are created.
     */
    public NumberCruncherDialog(){
        nca    = new NumberCruncherAnonym();
        nctl   = new NumberCruncherTopLevel();

        input  = new Scanner(System.in);
        option = new Scanner(System.in);

        operations = new String[]{"sum()", "swirl()", "divide()", "subtract()", "average()"};
        functions  = new String[5];
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
        if(funktion == FUNCTIONS){
            classMenu();
        } else if(funktion == CRUNCH){
            crunchMenu();
        } else if(funktion == PRINT){
            printMenu();
        } else if(funktion == END){
            System.out.println(TERMINATED);
        } else{
            System.out.println(INVALID_INPUT);
        }
    }

    // ################################## MENUS ###################################

    /**
     * Method which lets the user choose which array the functions should be
     * applied to..
     */
    private void classMenu(){
        // MENU TO CHOOSE A CLASS TO WORK WITH
        System.out.println(CLASS_MENU);
        String userInput = option.next();
        // ANONYMOUS
        if(userInput.equals("1")){
            nca.crunch(functions());
        // TOP LEVEL
        } else if(userInput.equals("2")){
            nctl.crunch(functions());
        // INVALID
        } else{
            System.out.println(INVALID_INPUT);
        }
    }

    /**
     * Method which lets the user choose which array should be crunched.
     */
    private void crunchMenu(){
        // MENU TO CHOOSE WHICH ARRAY TO CRUNCH
        System.out.println(CLASS_MENU);
        String userInput = option.next();
        // ANONYMOUS
        if(userInput.equals("1")){
            nca.crunch(operations);
        // TOP LEVEL
        } else if(userInput.equals("2")){
            nctl.crunch(operations);
        // INVALID
        } else{
            System.out.println(INVALID_INPUT);
        }
    }
    /**
     * Method which lets the user choose which array to print.
     */
    private void printMenu(){
        // MENU TO CHOOSE A WHICH ARRAY TO PRINT
        System.out.println(CLASS_MENU);
        String userInput = option.nextLine();
        // ANONYMOUS
        if(userInput.equals("1")){
//            nca.getNumbers();
            System.out.println(nca);
        // TOP LEVEL
        } else if(userInput.equals("2")){
//            nctl.getNumbers();
            System.out.println(nctl);
        // INVALID
        } else{
            System.out.println(INVALID_INPUT);
        }
    }

    /**
     *  Method which returns a custom filled String array which is later used as
     *  a parameter for the crunch methods.
     *  @return String[] operations
     */
    private String[] functions(){
        String userInput = option.nextLine();
        for(int i = 0; i <= functions.length - 1; i++){
        System.out.println(FUNCTIONS_MENU);
        userInput = option.nextLine();
            // SUMMIZE
            if(userInput.equals("1")){
                functions[i] = "sum()";
            // SWIRL
            } else if(userInput.equals("2")){
                functions[i] = "swirl()";
            // DIVIDE
            } else if(userInput.equals("3")){
                functions[i] = "divide()";
            // SUBTRACT
            } else if(userInput.equals("4")){
                functions[i] = "subtract()";
            // AVERAGE
            } else if(userInput.equals("5")){
                functions[i] = "average()";
            // END
            } else if(userInput.equals("0")){
                return functions;
            // INVALID
            } else{
                System.out.println(INVALID_INPUT);
            }
        }
        return functions;
    }


    // ############################################################################

    /**
     * Main method to create the dialogue-object.
     */
    public static void main (String[]args){
       new NumberCruncherDialog().start();
    }
}
