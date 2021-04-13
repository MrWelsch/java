package LambdaFunctions;

// IMPORTS

/**
 *  Result class which is used to display a Result object in the ArrayList
 *  implemented in the LambdaFunctions class.
 *  Every input gets a assigned result when the object is displayed.
 *  @author Nico Welsch, Oliver Theobald
 *  @version 1.0
 */
public class Result{

    // CLASS ATTRIBUTES
    final int input;
    final int result;

    // CLASS OBJECTS

    // CLASS CONSTANTS
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN  = "\u001B[36m";

    /**
     *  Constructor which is used to assign input parameters to an object.
     *  @param input  input integer i used in the applyAndPrint method in
     *                the LambdaFunctions class
     *  @param result integer which gets calculated by the various different
     *                functions in this project.
     */
    public Result(final int input, final int result){
        this.input  = input;
        this.result = result;
    }

    /**
     *  Getter for the input attribute to print out said attribute.
     */
    public int getInput(){
        return input;
    }

    /**
     *  Getter for the input attribute to print out said attribute.
     */
    public int getResult(){
        return result;
    }

    /**
     * ToString-Method to return the object as a String.
     */
    public String toString(){
        return(input + ANSI_CYAN  +          "    ->    "
                     + ANSI_RESET + result + "\n"
               );
    }
}
