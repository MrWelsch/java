package Queue;

/**
 * Class to create an ObjectQueue as an array.
 * @author Nico Welsch, Oliver Theobald
 * @version 1.0
 */
public class ObjectQueue implements Queue{

    // CLASS CONSTANTS
    public static final int QUEUE_STANDARD_LENGTH    = 10;

    private static final String  QUEUE_IS_FULL       = "QUEUE IS FULL";
    private static final String  QUEUE_IS_EMPTY      = "QUEUE IS EMPTY";
    private static final String  INDEX_OUT_OFF_RANGE = "INDEX IS BIGGER THAN QUEUE SIZE";
    private static final String  QUEUE_LENGTH_FAULTY = "QUEUE LENGTH MUST BE >= 0";

    // CLASS ATTRIBUTES
    protected Object[] queue;
    protected int size;

    /**
     * Constructor
     */
    public ObjectQueue(){
        queue = new Object[QUEUE_STANDARD_LENGTH];
        size = 0;
    }

    /**
     * Constructor with parameter
     * @param laenge
     */
    public ObjectQueue(int length) {
        if(length <= 0){
            throw new RuntimeException(QUEUE_LENGTH_FAULTY);
        }
        queue = new Object[length];
        size = 0;
    }

    /**
     * Method to add an element to the queue.
     * Req.: !full()
     */
    public void addLast(Object o){
        if(full()){
            throw new RuntimeException(QUEUE_IS_FULL);
        }
        queue[size] = o;
        size++;
    }

    /**
     * Method to remove the first element in the queue.
     * Req.:: !empty()
     * @return first element of the queue
     */
    public Object removeFirst(){
        if(empty()){
            throw new RuntimeException(QUEUE_IS_EMPTY);
        }
        int i;
        Object first = queue[0];
        size--;
        for(i = 0; i < size; i++){
            queue[i] = queue[i+1];
        }
        queue[i] = null;
        return first;
    }

    /**
     * Method the return the i'th element of the queue.
     * Req.: !empty()
     * @return i'th element of the queue
     */
    public Object get(int i){
        if(empty()){
            throw new RuntimeException(QUEUE_IS_EMPTY);
        } else if(i > size){
            throw new RuntimeException(INDEX_OUT_OFF_RANGE);
        }
        return queue[i];
    }

    /**
     * Method to check if the queue is full.
     * @return true  if the queue is full
     *         false if it isn't
     */
    public boolean full(){
        return (size == queue.length);
    }

    /**
     * Method to check if the queue is empty.
     * @return true  if the queue is empty
     *         false if it isn't
     */
    public boolean empty(){
        return (size == 0);
    }

    /**
     * Method to check how many elements are in the queue.
     * @return queue size
     */
    public int size(){
        return size;
    }

    /**
     * ToString-Method to return the object as a String.
     */
    public String toString(){
        StringBuffer sb = new StringBuffer();
        for(int index = 0; index < size; index++){
            sb.append(queue[index] + " - ");
        }
        return sb.toString();
    }
}
