package NumberCruncher;

/**
 *
 */
public class Divide implements CrunchOperation{
    /**
     * Standard constructor to prevent the unwanted creation of an object.
     */
    public Divide(){
    }
    /**
     *  Method to divide the biggest float value by the smallest one, the
     *  second biggest by the second smallest and so on.
     *  Example: a[0]= 5.97862
     *           a[1]= 2.94832
     *           a[2]= 0.20345
     *           a[3]= 0.93567
     *           -> a[0] will be divided by a[2]
     *              a[1] will be divided by a[3]
     */
    @Override
    public void crunch(float values[]){
        int biggest  = 0;
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
}
