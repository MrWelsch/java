package LambdaFunctions;

// IMPORTS
import java.util.ArrayList;
import java.util.List;

/**
 *  LambdaFunctions Class which calculates the results of different functions in
 *  multiple ways.
 *  Those consist of Lambdas, Anonymous classes, Top Level classes and Static
 *  Nested classes.
 *  @author Nico Welsch, Oliver Theobald
 *  @version 1.0
 */
public class LambdaFunctions implements MyFunction{

    // CLASS ATTRIBUTES

    // CLASS OBJECTS
    private Result       result;
    private List<Result> results;

    // CLASS CONSTANTS
    private static final String ANSI_RESET          = "\u001B[0m";
    private static final String ANSI_CYAN           = "\u001B[36m";

    private static final String RESULTS             =                                              "\n" +
                                                                    "--------------------------" + "\n" +
                                                      ANSI_CYAN   + "RESULTS                   " + "\n" +
                                                      ANSI_RESET                                 + "\n" ;

    /**
     *  Constructor which initializes the ArrayList results.
     */
    public LambdaFunctions(){
        results = new ArrayList<>();    // Nur hier als ArrayList deklarieren, da Abhaengigkeitsimplementierung nur an dieser Stelle.
    };

    /**
     *  Method to apply an input integer value to a function.
     *  @param  integer value
     *  @return integer value
     */
    @Override
    public int apply(int i){
        return i;
    }

    /**
     *  Method which runs through a function for each integer value between two
     *  previously input ones and adds the results of to the ArrayList results.
     *  @param integer values and an object from the interface MyFunction
     *  @return ArrayList results
     */
    public List<Result> applyAndPrint(int i, int j, MyFunction function){  // Hier List nutzen da ArrayList ist eine Schnittstelle nach aussen!
        for (; i <= j; i++) {
            result = new Result(i, function.apply(i));
            results.add(result);
        }
        return results;
    }

    /**
     *  Factorial static nested class which alters the apply method to calculate
     *  the factorial of a number.
     */
    static class Factorial implements MyFunction{

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

    /**
     * ToString-Method to return the object as a String.
     */
    public String toString(){
        StringBuffer sb = new StringBuffer();
        System.out.println(RESULTS);
        for(int i = 0; i < results.size(); i++){
            sb.append(results.get(i) + "\n");
        }
        return sb.toString();
    }
}
