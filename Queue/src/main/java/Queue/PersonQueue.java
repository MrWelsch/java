package Queue;

// IMPORTS
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Class to create a PersonQueue with the general ObjectQueue.
 * @author Nico Welsch, Oliver Theobald
 */
public class PersonQueue extends ObjectQueue{

    //OBJECTS
    PersonIterator pitr;

    // CLASS CONSTANTS
    private static final String NOT_A_PERSON = "ENTERED OBJECT IS NOT A PERSON";

    /**
     * Constructor
     */
    public PersonQueue(int length) {
        super(length);
        pitr = this.new Iterator();
    }

    /**
     * Inner class which implements the PersonIterator interface and adds the
     * functions of an Iterator to the PersonQueue class.
     */
    private class Iterator implements PersonIterator{

        // INNER CLASS ATTRIBUTES
        private int i;

        /**
         *
         */
        @Override
        public boolean hasNext(){
            if(i < size){
                return true;
            } else{
                return false;
            }
        }

        /**
         *
         */
        @Override
        public Person next() throws NoSuchElementException{
            if(hasNext() == true){
                System.out.println(queue[i++]);
            } else{
                throw new NoSuchElementException();
            }
            return null;
        }
    }

    /**
     * Method to add a Person object to the end of the queue.
     * Req.: !full()
     * @param o object to add
     */
    @Override
    public void addLast(Object o){
        if(o instanceof Person){
            super.addLast(o);
        } else{
            throw new RuntimeException(NOT_A_PERSON);
        }
    }

    /**
     * Method to remove a Person object at the top of the queue and return it.
     * Req.: !empty()
     * @return Person object
     */
    @Override
    public Person removeFirst(){
    return (Person)super.removeFirst();
    }

    /**
     * Method to get the i'th element of the queue.
     * Req.: !empty()
     * @return i'th element
     */
    @Override
    public Person get(int i){
        return (Person)super.get(i);
    }

    /**
     *
     */
    public String smallest(){
        String smallest = "";
        String[] s = Arrays.stream(queue).toArray(String[]::new);
        for(int i = 0; pitr.hasNext(); i++){
            if(s[i].compareTo(s[i++]) > 0){
                smallest = s[i];
           };
        }
        return smallest;
    }

    /**
     *
     */
    public String toString(){
        String s;
        while(pitr.hasNext()){
            s = pitr.next() + "";
            return s;
        }
        // PRINT EMPTY LINE IF THERE ARE NO OBJECTS TO PRINT ANYMORE
        return "";
    }
}
