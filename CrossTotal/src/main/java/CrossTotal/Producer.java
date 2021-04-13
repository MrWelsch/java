package CrossTotal;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Consumer Class to..
 * @author Nico Welsch, Oliver Theobald
 * @version 1.0
 */
public class Producer{


    /**
     * Prevents the unwated creation of class objects.
     */
    public Producer() {
    }

    /**
     * Creates a random Integer between 0 and 1000.
     * @return random integer
     */
    public static int produce(){
        int randomNumber;
        int min = 0;
        int max = 1000;
        randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
        // added max + 1 to include the top value, else it would be excluded
        // because of how nextInt works.
        return randomNumber;
    }

}
