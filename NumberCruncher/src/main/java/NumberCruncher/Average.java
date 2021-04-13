package NumberCruncher;

/**
 *
 */
public class Average implements CrunchOperation{
    /**
     * Standard constructor to prevent the unwanted creation of an object.
     */
    public Average(){
    }
    /**
     *  Method to calculate and save the average of all floats inside the array
     *  to slot with the biggest float.
     */
    @Override
    public void crunch(float values[]){
        for(int i = 0; i <= values.length -2; i++){
            values[values.length - 1] += values[i];
        }
        values[values.length - 1] /= values.length;
    }
}
