package NumberCruncher;

/**
 *
 */
public class Subtract implements CrunchOperation{
    /**
     * Standard constructor to prevent the unwanted creation of an object.
     */
    public Subtract(){
    }
    /**
     *  Method to subtract the floats inside the array pairwise from left to
     *  right and save each resulting value in the next slot.
     *  Example: a[1] = a[0] - a[1]
     *           a[2] = a[1] - a[2]
     */
    @Override
    public void crunch(float values[]){
        for(int i = 0; i <= values.length -1; i++){
            values[i+1] -= values[i];
        }
    }
}
