package Queue;

/**
 * Class to create a StringQueue iwith the general ObjectQueue.
 * @author Nico Welsch, Oliver Theobald
 */
public class StringQueue extends ObjectQueue{

    // CLASS CONSTANTS
    private static final String  NOT_A_STRING = "ENTERED OBJECT IS NOT A STRING";

    /**
     * Constructor
     */
    public StringQueue(int length){
        super(length);
    }

    /**
     * Method to add a String object to the end of the queue.
     * Req.: !full()
     * @param o object to add
     */
    @Override
    public void addLast(Object o){
        if(o instanceof String){
            super.addLast(o);
        } else{
            throw new RuntimeException(NOT_A_STRING);
        }
    }

    /**
     * Method to remove a String object at the top of the queue and return it.
     * Req.: !empty()
     * @return string object
     */
    @Override
    public String removeFirst(){
        return (String)super.removeFirst();
    }

    /**
     * Method to get the i'th element of the queue.
     * Req.: !empty()
     * @return i'th element
     */
    @Override
    public String get(int i){
        return (String)super.get(i);
    }
}
