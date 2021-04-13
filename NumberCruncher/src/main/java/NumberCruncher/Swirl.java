package NumberCruncher;

// IMPORTS
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 */
public class Swirl implements CrunchOperation{
    /**
     * Standard constructor to prevent the unwanted creation of an object.
     */
    public Swirl(){
    }
    /**
     *  Method to randomly change the order of the array slots n-times.
     *  n = array.length
     */
    @Override
    public void crunch(float values[]){
        Random rnd = ThreadLocalRandom.current();
        int   index;
        float swap;
        for(int i = values.length -1; i > 0; i--){
            index         = rnd.nextInt(i + 1);
            swap          = values[index];
            values[index] = values[i];
            values[i]     = swap;
        }
    }
}
