package LambdaFunctions;

// IMPORTS
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.IntPredicate;

/**
 *  Dialogue class to interacitvely test the previously programmed functions of
 *  the LambdaFunctions class.
 *  @author Nico Welsch, Oliver Theobald
 *  @version 1.0
 */
public class LambdaFunctionsDialog{

    // OBJECTS
    private LambdaFunctions           exec;
    private LambdaFunctions.Factorial snc;
    private Factorial                 tlc;

    private Scanner                   input;

    // LAMBDAS
    private Conditionate square;
    private Conditionate factorial;
    private MyFunction   squareplusone;
    private MyFunction   fibonacci;                  // ALS BIG INTEGER  WIEDERGEBEN MOEGLICHKEIT?

    private MyFunction                facsnc;
    private MyFunction                factlc;

    // PREDICATES
    private IntPredicate even;

    // CLASS CONSTANTS
    private static final int SQUARE                 = 1;
    private static final int SQUARE_PLUS            = 2;
    private static final int FACTORIAL              = 3;
    private static final int FIBONACCI              = 4;
    private static final int END                    = 0;

    private static final String ANSI_RESET          = "\u001B[0m";
    private static final String ANSI_RED            = "\u001B[31m";
    private static final String ANSI_GREEN          = "\u001B[32m";
    private static final String ANSI_PURPLE         = "\u001B[35m";
    private static final String ONE                 = "1";
    private static final String TWO                 = "2";
    private static final String THREE               = "3";
    private static final String FOUR                = "4";
    private static final String FIVE                = "5";

    private static final String INT_INPUT           =                                    "\n" +
                                                      ANSI_GREEN  + "INPUT AN INTEGER" + "\n" +
                                                      ANSI_RESET                       + "\n" ;

    private static final String TERMINATED          =                                    "\n" +
                                                      ANSI_RED    + "TERMINATED      " + "\n" ;

    // MENU LAYOUTS

    private static final String MAIN_MENU           =                                                                 "\n" +
                                                                                       "--------------------------" + "\n" +
                                                                                                                      "\n" +
                                                      SQUARE      +                    ": SQUARE                  " + "\n" +
                                                      SQUARE_PLUS +                    ": SQUARE + 1              " + "\n" +
                                                      FACTORIAL   +                    ": FACTORIAL               " + "\n" +
                                                      FIBONACCI   +                    ": FIBONACCI               " + "\n" +
                                                      END         + ":" + ANSI_RED   +  " END                     " + "\n" +
                                                                          ANSI_RESET +                                "\n" +
                                                                                       "--------------------------" + "\n" +
                                                                          ANSI_GREEN +                                "\n" +
                                                                                       "->           "              +
                                                                          ANSI_RESET ;

    private static final String FUNCTION_TYPE       =                                                                 "\n" +
                                                                                       "--------------------------" + "\n" +
                                                                                                                      "\n" +
                                                                         ANSI_PURPLE + "CHOOSE AN OPTION          " + "\n" +
                                                                         ANSI_RESET  +                                "\n" +
                                                                                       "1: LAMBDA                 " + "\n" +
                                                                                       "2: INNER CLASS            " + "\n" +
                                                                                                                      "\n" +
                                                                                       "--------------------------" + "\n" +
                                                                          ANSI_GREEN +                                "\n" +
                                                                                       "->           "              +
                                                                          ANSI_RESET ;

    private static final String SQUARE_FUNCTIONS    =                                                                 "\n" +
                                                                                       "--------------------------" + "\n" +
                                                                                                                      "\n" +
                                                                         ANSI_PURPLE + "CHOOSE AN OPTION          " + "\n" +
                                                                         ANSI_RESET  +                                "\n" +
                                                                                       "1: LAMBDA                 " + "\n" +
                                                                                       "2: INNER CLASS            " + "\n" +
                                                                                       "3: TEST FOR EVEN INPUT    " + "\n" +
                                                                                                                      "\n" +
                                                                                       "--------------------------" + "\n" +
                                                                          ANSI_GREEN +                                "\n" +
                                                                                       "->           "              +
                                                                          ANSI_RESET ;

    private static final String FACTORIAL_FUNCTIONS =                                                                 "\n" +
                                                                                       "--------------------------" + "\n" +
                                                                                                                      "\n" +
                                                                         ANSI_PURPLE + "CHOOSE AN OPTION          " + "\n" +
                                                                         ANSI_RESET  +                                "\n" +
                                                                                       "1: LAMBDA                 " + "\n" +
                                                                                       "2: INNER CLASS            " + "\n" +
                                                                                       "3: TOP LEVEL CLASS        " + "\n" +
                                                                                       "4: STATIC NESTED CLASS    " + "\n" +
                                                                                       "5: TEST FOR UNEVEN OUTPUT " + "\n" +
                                                                                                                      "\n" +
                                                                                       "--------------------------" + "\n" +
                                                                          ANSI_GREEN +                                "\n" +
                                                                                       "->           "              +
                                                                          ANSI_RESET ;

    /**
     *  Constructor in which objects are created.
     */
    public LambdaFunctionsDialog(){
        exec   = new LambdaFunctions();
        tlc    = new Factorial();
        snc    = new LambdaFunctions.Factorial();

        input  = new Scanner(System.in);

        // LAMBDAS
        square        = i -> i * i;
        factorial     = i -> i == 0 ? 1 : i * this.factorial.apply(--i);
        squareplusone = i -> i*(i++);
        fibonacci     = i -> i <= 2 ? 1 : (int)(Math.round(Math.pow((1 + Math.sqrt(5)) / 2, i) / Math.sqrt(5)));
//                                              BINET'S FORMULA FOR nth FIBONACCI NUMBER
//                                                  Fn = {[(√5 + 1)/2] ^ n} / √5

        facsnc        = i -> snc.apply(i);    // IMPLEMENTIERUNG SO IN ORDNUNG ?
        factlc        = i -> tlc.apply(i);    // IMPLEMENTIERUNG SO IN ORDNUNG ?

        // PREDICATES
        even = i -> i % 2 == 0;    // Zero all the bits but leave the least significant
                                   // bit unchanged and check if the result is
                                   // 0.
                                   // i & 1 == 0 instead of i % 2 == 0
    }

     /**
     *  Main loop for the dialogue-programm which trys to execute the user
     *  interface and the assigned functions as long as the programme
     *  doesn't catch an exception.
     *  If it catches an exception the programm will print out the exception
     *  so that the user can see what went wrong.
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
     *  Method to create an user interface which is able to read the user input
     *  and execute the assigned function.
     */
    private int einleseFunktion(){
        int funktion;
        System.out.print(MAIN_MENU);
        funktion = input.nextInt();
        input.nextLine();
        return funktion;
    }

    /**
     *  Method to assign each expression a method which runs if the function is
     *  triggered.
     *  If none of the functions are triggered after an input the programm will throw an IllegalArgumentException().
     *  After executing the assigned method the programm will then print out
     *  the output of the executed function.
     */
    private void ausfuehrenFunktion(int funktion){
        if(funktion == SQUARE){
            squareMenu();
        } else if(funktion == SQUARE_PLUS){
            squarePlusMenu();
        } else if(funktion == FACTORIAL){
            factorialMenu();
        } else if(funktion == FIBONACCI){
            fibonacciMenu();
        } else if(funktion == END){
            System.out.println(TERMINATED);
        } else{
            throw new IllegalArgumentException();
        }
    }

//  -------------------------------- MENUS --------------------------------

    /**
     *  Method which lets the user choose between different square functions.
     */
    private void squareMenu(){
        // MENU TO CHOOSE A SQUARE FUNCTION TO CALCULATE WITH
        System.out.print(SQUARE_FUNCTIONS);
        String userInput = input.next();
        // LAMBDA
        if(userInput.equals(ONE)){
            exec.applyAndPrint(readInt(), readInt(), square);
            System.out.println(exec);
            exec = new LambdaFunctions();
        // INNER CLASS
        } else if(userInput.equals(TWO)){
            exec.applyAndPrint(readInt(), readInt(),
                                /**
                                 *  Anonymous class to return a square function
                                 *  as a MyFunction object.
                                 *  @return function as a MyFunction object
                                 */
                                new MyFunction() {
                                    /**
                                     *  Method to apply an input integer value to a function.
                                     *  @param  integer value
                                     *  @return squared integer value
                                     */
                                    @Override
                                    public int apply(int i){
                                        return i * i;
                                    }
                                }
                               );
            System.out.println(exec);
            exec = new LambdaFunctions();
        // CHECK FOR EVEN INPUT WITH LAMBDA
        } else if(userInput.equals(THREE)){
            exec.applyAndPrint(readInt(), readInt(), square.conditionateInput(even));
            System.out.println(exec);
            exec = new LambdaFunctions();
        } else{
            throw new IllegalArgumentException();
        }
    }

    /**
     *  Method which lets the user choose between different squarePlus functions.
     */
    private void squarePlusMenu(){
        // MENU TO CHOOSE A SQUARE FUNCTION TO CALCULATE WITH
        System.out.print(FUNCTION_TYPE);
        String userInput = input.next();
        // LAMBDA
        if(userInput.equals(ONE)){
            exec.applyAndPrint(readInt(), readInt(), squareplusone);
            System.out.println(exec);
            exec = new LambdaFunctions();
        // INNER CLASS
        } else if(userInput.equals(TWO)){
            exec.applyAndPrint(readInt(), readInt(),
                                /**
                                 *  Anonymous class to return a square+1 function
                                 *  as a MyFunction object.
                                 *  @return function as a MyFunction object
                                 */
                                new MyFunction() {
                                    /**
                                     *  Method to apply an input integer value to a function.
                                     *  @param  integer value
                                     *  @return squared integer value
                                     */
                                    @Override
                                    public int apply(int i){
                                        return i * (i + 1);
                                    }
                                }
                               );
            System.out.println(exec);
            exec = new LambdaFunctions();
        } else{
            throw new IllegalArgumentException();
        }
    }

    /**
     *  Method which lets the user choose between different factorial functions.
     */
    private void factorialMenu(){
        // MENU TO CHOOSE A FACTORIAL FUNCTION TO CALCULATE WITH
        System.out.print(FACTORIAL_FUNCTIONS);
        String userInput = input.next();
        // LAMBDA
        if(userInput.equals(ONE)){
            exec.applyAndPrint(readInt(), readInt(), factorial);
            System.out.println(exec);
            exec = new LambdaFunctions();
        // INNER CLASS
        } else if(userInput.equals(TWO)){
            exec.applyAndPrint(readInt(), readInt(),
                                /**
                                 *  Anonymous class to return a factorial function
                                 *  as a MyFunction object.
                                 *  @return function as a MyFunction object
                                 */
                                new MyFunction() {
                                    /**
                                     *  Method to apply an input integer value to a function.
                                     *  @param  integer value
                                     *  @return factorial of an integer value
                                     */
                                    @Override
                                    public int apply(int i){
                                        if(i == 1){
                                            return i;
                                        } else {
                                            return i * apply(--i);
                                        }
                                    }
                                }
                               );
            System.out.println(exec);
            exec = new LambdaFunctions();
        // TOP LEVEL CLASS
        } else if(userInput.equals(THREE)){
            exec.applyAndPrint(readInt(), readInt(), factlc);
            System.out.println(exec);
            exec = new LambdaFunctions();
        // STATIC NESTED CLASS
        } else if(userInput.equals(FOUR)){
            exec.applyAndPrint(readInt(), readInt(), facsnc);
            System.out.println(exec);
            exec = new LambdaFunctions();
        // CHECK FOR UNEVEN OUTPUT WITH LAMBDA
        } else if(userInput.equals(FIVE)){
            exec.applyAndPrint(readInt(), readInt(), factorial.conditionateOutput(
                                /**
                                 *  Anonymous class to return a factorial function
                                 *  as a MyFunction object.
                                 *  @return function as a MyFunction object
                                 */
                                new IntPredicate() {
                                    /**
                                     *  Method to apply an input integer value to a function.
                                     *  @param  integer value
                                     *  @return factorial of an integer value
                                     */
                                    @Override
                                    public boolean test(int i){
                                        if(i % 2 == 1){
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    }
                                }
                              ));    //FUNKTIONIERT NOCH NICHT
            System.out.println(exec);
            exec = new LambdaFunctions();
        } else{
            throw new IllegalArgumentException();
        }
    }

    /**
     *  Method which lets the user choose between different fibonacci functions.
     */
    private void fibonacciMenu(){
        // MENU TO CHOOSE A SQUARE FUNCTION TO CALCULATE WITH
        System.out.print(FUNCTION_TYPE);
        String userInput = input.next();
        // LAMBDA
        if(userInput.equals(ONE)){
            exec.applyAndPrint(readInt(), readInt(), fibonacci);
            System.out.println(exec);
            exec = new LambdaFunctions();
        // INNER CLASS
        } else if(userInput.equals(TWO)){
            exec.applyAndPrint(readInt(), readInt(),
                                /**
                                 *  Anonymous class to return a fibonacci function
                                 *  as a MyFunction object.
                                 *  @return function as a MyFunction object
                                 */
                                new MyFunction() {
                                    /**
                                     *  Method to apply an input integer value to a function.
                                     *  @param  integer value
                                     *  @return nth fibonacci number as an
                                     *          integer value
                                     */
                                    @Override
                                    public int apply(int i){
                                        if(i <= 2){
                                            return 1;
                                        } else {
                                            return (int)(Math.round(Math.pow((1 + Math.sqrt(5)) / 2, i) / Math.sqrt(5)));
                                        }
                                    }
                                }
                               );
            System.out.println(exec);
            exec = new LambdaFunctions();
        } else{
            throw new IllegalArgumentException();
        }
    }

//  ------------------------------- INPUTS --------------------------------

    /**
     *  Method which lets the user enter an int value which can be used in
     *  methods previously defined in the LambdaFunctions class.
     *  @return int value
     */
    private int readInt(){
        int n;
        System.out.print(INT_INPUT);
        n = input.nextInt();
        return n;
    }

    /**
     * Main method to create the dialogue-object.
     */
    public static void main (String[]args){
       new LambdaFunctionsDialog().start();
    }
}
