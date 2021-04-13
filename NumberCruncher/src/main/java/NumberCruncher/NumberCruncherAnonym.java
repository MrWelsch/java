package NumberCruncher;

// IMPORTS
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 */
public class NumberCruncherAnonym{
    // CLASS ATTRIBUTES

    // OBJECTS
    private float[]  values;

    // CLASS CONSTANTS
    private static final String ANSI_RESET     = "\u001B[0m";
    private static final String ANSI_RED       = "\u001B[31m";
    private static final String ANSI_GREEN     = "\u001B[32m";
    private static final String ANSI_PURPLE    = "\u001B[35m";

    private static final String TO_STRING      =                                "\n" +
                                                 ANSI_GREEN + "ARRAY        " +
                                                 ANSI_RESET ;

    private static final String INVALID_INPUT  =                                "\n" +
                                                 ANSI_RED   + "INVALID INPUT" +
                                                 ANSI_RESET +                   "\n" ;

    /**
     *
     */
    public NumberCruncherAnonym(){
        values = new float[] {1,2,3,4,5,6,7,8,9,10};
    }

    /**
     * Definition of an anonymous class
     */
    Sum sum = new Sum(){
        /**
         *  Method to summize the floats inside the array pairwise from left to
         *  right and save each resulting value in the next slot.
         *  Example: a[1] = a[0] + a[1]
         *           a[2] = a[1] + a[2]
         */
        public void crunch(){
            for(int i = 0; i < values.length - 1; i++){
                values[i+1] += values[i];
            }
        }
    };

    /**
     * Definition of an anonymous class
     */
    Swirl swirl = new Swirl(){
        /**
         *  Method to randomly change the order of the array slots n-times.
         *  n = array.length
         *  Durstenfeld Shuffle
         */
        public void crunch(){
            Random rnd = ThreadLocalRandom.current();
            int   index;
            float swap;
            for(int i = values.length -1; i > 0; i--){
                index          = rnd.nextInt(i + 1);
                swap           = values[index];
                values[index]  = values[i];
                values[i]      = swap;
            }
        }
    };

    /**
     * Definition of an anonymous class
     */
    Divide divide = new Divide(){
        /**
         *  Method to divide the biggest float value by the smallest one, the
         *  second biggest by the second smallest and so on.
         *  Example: a[0]= 5.97862
         *           a[1]= 2.94832
         *           a[2]= 0.20345
         *           a[3]= 0.93567
         *           -> a[0] will be divided by a[2]
         *              a[1] will be divided by a[3]
         *           The results will be saved in the bigger one.
         */
        public void crunch(){
            int biggest = 0;
            int smallest = 0;

            for(int j = 0; j <= values.length -1; j++){
                for(int i = 0; i <= values.length -1; i++){
                    if(values[i+1] > values[i]){
                        biggest  = i+1;
                        smallest = i;
                    } else{
                        biggest  = i;
                        smallest = i+1;
                    }
                }
                if(smallest != 0){
                    values[biggest] /= values[smallest];
                }
            }
        }
    };

    /**
     * Definition of an anonymous class
     */
    Subtract subtract = new Subtract(){
        /**
         *  Method to substract the floats inside the array pairwise from left to
         *  right and save each resulting value in the next slot.
         *  Example: a[1] = a[0] - a[1]
         *           a[2] = a[1] - a[2]
         */
        public void crunch(){
            for(int i = 0; i <= values.length -1; i++){
                values[i+1] -= values[i];
            }
        }
    };

    /**
     * Definition of an anonymous class
     */
    Average average = new Average(){
        /**
         *  Method to calculate and save the average of all floats inside the array
         *  to slot with the biggest float.
         */
        public void crunch(){
            for(int i = 0; i <= values.length -2; i++){
                values[values.length - 1] += values[i];
            }
            values[values.length - 1] /= values.length;
        }
    };

    /**
     *  Method to crunch the array, meaning the given String array has operations
     *  which will be executed on the float array one after the other.
     */
    public void crunch(String[] operations){
        //for(int i = 0; i <= operations.length -1; i++){
        //    for(int j = 0; j <= numbers.length -1; j++){
        //        numbers[j].operations[i];
        //    }
        //}
        for(int i = 0; i <= operations.length -1; i++){ // Access to anonymous classes?
            if(operations[i].equals("sum()")){
                sum.crunch(values);
            } else if(operations[i].equals("swirl()")){
                swirl.crunch(values);
            } else if(operations[i].equals("divide()")){
                divide.crunch(values);
            } else if(operations[i].equals("subtract()")){
                subtract.crunch(values);
            } else if(operations[i].equals("average()")){
                average.crunch(values);
            } else{
                System.out.println(INVALID_INPUT);
            }
        }
    }

    /**
     * Method to get the float array printed out.
     */
    public float[] getNumbers(){
        return values;
    }

    /**
     * ToString-Method to return the object as a String.
     */
    public String toString(){
        StringBuffer sb = new StringBuffer();
        System.out.println(TO_STRING);
        for(int i = 0; i < values.length; i++){
            sb.append(values[i] + " - ");
        }
        return sb.toString();
    }
}
