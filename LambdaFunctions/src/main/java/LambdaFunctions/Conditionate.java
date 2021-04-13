package LambdaFunctions;

// IMPORTS
import java.util.function.*;

/**
 *  Conditionate interface which hosts two methods that are used to conditionate
 *  Inputs and Outputs of functions in the LambdaFunctions class.
 *  @author Nico Welsch, Oliver Theobald
 *  @version 1.0
 */
public interface Conditionate extends MyFunction{

    /**
     *  Method to return a Lambda which checks if given predicate is true
     *  for an integer value.
     *  @param IntPredicate
     *  @return If true  == result of the apply()-Method
     *             false == 0
     */
    default MyFunction conditionateInput(IntPredicate predicate){
        MyFunction function = i -> predicate.test(i) == true ? 1 : 0;
        return function;
    }

    /**
     *  Method to return a Lambda which checks if the given predicate is false
     *  for the result of the apply()-Method.
     *  @param IntPredicate
     *  @return If false == result of the apply()-Method
     *             true  == 0
     */
    default MyFunction conditionateOutput(IntPredicate predicate){
        MyFunction function = i -> predicate.test(apply(i)) == false ? 1 : 0;
        return function;
    }

}
