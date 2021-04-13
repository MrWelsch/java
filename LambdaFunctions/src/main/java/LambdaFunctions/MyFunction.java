package LambdaFunctions;

/**
 *  Functional MyFunction interface which hosts the apply method that is used in
 *  the LambdaFunctions class.
 *  @author Nico Welsch, Oliver Theobald
 *  @version 1.0
 */
public interface MyFunction{

    /**
     *  Method to apply an input integer value to a function.
     *  @param  integer value
     *  @return integer value
     */
    public int apply(int i);

}
