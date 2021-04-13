package Queue;

/**
 * Interface for the Queue project.
 * @author Nico Welsch, Oliver Theobald
 * @version 1.02
 */
public interface Queue{
    /**
     * Method to add an object to the end of the queue.
     * Req.: !full()
     * @param o object to add
     */
    public void addLast(Object o);

    /**
     * Method to remove an object at the top of the queue and return it.
     * Req.: !empty()
     */
    public Object removeFirst();

    /**
     * Method to get the i'th element of the queue.
     * Req.: !empty()
     * @return i'th element
     */
    public Object get(int i);

    /**
     * Method to check if the queue is empty.
     * @return true  if the queue is empty
     *         false if it isn't
     */
    public boolean empty();

    /**
     * Method to check if the queue is full.
     * @return true  if the queue is full
     *         false if it isn't
     */
    public boolean full();

    /**
     * Method to check how many elements are in the queue.
     * @return queue size
     */
    public int size();
}
