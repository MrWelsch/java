package LambdaFunctions;

// IMPORTS

/**
 *  Factorial class which contains a method to calculate the factorial of a
 *  number.
 *  @author Nico Welsch, Oliver Theobald
 *  @version 1.0
 */
public class Factorial implements MyFunction{

    // CLASS ATTRIBUTES

    // CLASS OBJECTS

    // CLASS CONSTANTS

    /**
     *  Standard Constructor
     */
    public Factorial(){};

    /**
     *  Method to apply a pre-defined function to the applyAndPrint-Method.
     *  @param  integer value
     *  @return factorial as integer value
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

