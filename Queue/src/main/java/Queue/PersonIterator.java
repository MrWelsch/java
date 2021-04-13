package Queue;

/**
 * Interface for the Iterator of the Queue classes.
 * @author Nico Welsch, Oliver Theobald
 * @version 1.0
 */
public interface PersonIterator extends java.util.Iterator<Person>{
    public boolean hasNext();

    public Person next();
}
