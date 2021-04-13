package CrossTotal;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Consumer Class to..
 * @author Nico Welsch, Oliver Theobald
 * @version 1.0
 */
public class Consumer{

    private Map<Integer, Collection<Long>> map;     // MAPS DO NOT ALLOW DUPLICATE KEYS !
    private NavigableSet<Integer> set;

    /**
     *
     */
    public Consumer(){
        map = new TreeMap<>();
        set = (NavigableSet<Integer>)map.keySet();
    }

    /**
     * Calculates the cross-total of an integer. The timestamp of each
     * calculation, meaning the every timestamp which lead to the cross-total,
     * is saved and can be efficiently accessed for each and every cross-total.
     * @param integer
     * @return cross-total
     */
    public void consume(int i){
        int result = 0;
        List<Long> list = new LinkedList<>();
        while (i > 0) {
            result = result + i % 10;
            list.add(System.currentTimeMillis());
            i = i / 10;
        }
        map.put(result, list);      // REPLACES KEY WITH IT'S DUPLICATE !
    }

    /**
     * Returns how many different cross-totals have been calculated.
     * @return amount of different cross-totals
     */
    public int numberOfDifferentResults(){
        set.stream()
           .filter(i -> Collections.frequency(set, i) > 1)
           .collect(Collectors.toSet());
        return set.size();
    }

    /**
     * Returns how often an integer occured as the result of a calculation.
     * @param integer
     * @return number of occurences
     */
    public int numberOfOccurrences(int i){
        return set.subSet(i, true, i, true)
                  .size();
    }

    /**
     * Returns a Collection which contains the cross-totals in ascending order.
     * @return collection
     */
    public Collection<Integer> getCrossTotalsAscending(){
        return map.keySet();
    }

    /**
     * Returns a Collection which contains the cross-totals in descending order.
     * @return collection
     */
    public Collection<Integer> getCrossTotalsDescending(){
        return set.descendingSet();
    }

    /**
     * Returns the timestamps of each calculation that lead to the resulting
     * cross-total.
     * @param integer
     * @return collection
     */
    public Collection<Long> getTimestampsForResult(int i){  // what about duplicates?
        return map.get(i);
    }

    /**
     * Returns the class object as customized String.
     */
    @Override
    public String toString() {
        return map.toString();
    }

}
