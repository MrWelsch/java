package NumberCruncher;

// IMPORTS
/**
 *
 */
public class NumberCruncherTopLevel{
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
    public NumberCruncherTopLevel(){
        values = new float[] {1,2,3,4,5,6,7,8,9,10};
    }

    /**
     *  Method to crunch the array, meaning the given String array has operations
     *  which will be executed on the float array one after the other.
     */
      public void crunch(String[] operations){
          for(int i = 0; i <= operations.length -1; i++){
              if(operations[i].equals("sum()")){
                 new  Sum().crunch(values);
              } else if(operations[i].equals("swirl()")){
                 new  Swirl().crunch(values);
              } else if(operations[i].equals("divide()")){
                 new  Divide().crunch(values);
              } else if(operations[i].equals("subtract()")){
                 new  Subtract().crunch(values);
              } else if(operations[i].equals("average()")){
                 new  Average().crunch(values);
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
