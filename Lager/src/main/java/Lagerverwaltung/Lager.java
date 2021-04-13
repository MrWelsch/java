package Lagerverwaltung;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Lager class to store created articles of the Article class for simple
 * inventory management.
 * @author  Nico Welsch, Oliver Theobald
 * @version 3.0
 */
public class Lager {

    private Map<Integer, Artikel> storage;

    /**
     * Constructor for the lager class which is used to create a lager object which
     * is an array of a fixed size (10).
     */
    public Lager() {
        storage = new LinkedHashMap<>();    // ITERATION IN ORDER OF CREATION
    }

    /**
     * method which uses the Article class to create an article object within the
     * lager array.
     *
     * @param article article object
     */
    public void legeAnArtikel(Artikel artikel) {
        if (storage.containsKey(artikel.getArtikelNr()) == false)
            storage.put(artikel.getArtikelNr(), artikel);
    }

    /**
     * method which removes a specific existing article object The findeArtikel
     * method is used to find the article.
     *
     * @param artikelNr entered value which specifies the article
     */
    public void entferneArtikel(int artikelNr) {
        storage.remove(artikelNr);
    }

    /**
     * method to add stock to a specific existing article object. The findeArtikel
     * method is used to find the article.
     *
     * @param artikelNr entered value which specifies the article
     * @param menge     entered value which specifies the amount
     */
    public void bucheZugang(int artikelNr, int menge) {
        storage.get(artikelNr).bucheZugang(menge);
    }

    /**
     * method which removes stock from a specific existing article object. The
     * findeArtikel method is used to find the article.
     *
     * @param artikelNr entered value which specifies the article
     * @param menge     entered value which specifies the amount
     */
    public void bucheAbgang(int artikelNr, int menge) {
        storage.get(artikelNr).bucheAbgang(menge);
    }

    /**
     * method which is used to change the price of all article objects
     *
     * @param prozent percentage value
     */
    public void aenderePreisAllerArtikel(double prozent) {
        storage.values().forEach(a -> a.aenderePreis(prozent));
    }

    /**
     * Turns the lager array into a list, which will be sorted according to the
     * given predicate.
     *
     * @param predicate sort criteria
     * @return sorted list
     */
    public static List<Artikel> sortList(List<Artikel> list, Comparator<Artikel> collector) {
        list.stream()
            .sorted(collector)
            .collect(Collectors.toList());
        return list;
    }

    /**
     * Combines multiple Lists into one by appending them in the order in which the
     * parameters are given to the method.
     *
     * @param lists array of lists
     * @return combined list.
     */
    @SuppressWarnings("unchecked")
    public static List<Artikel> appendLists(List<Artikel>... lists) {
        List<Artikel> list = new ArrayList<>();
        for (int i = 0; i < lists.length; i++) {
            list.addAll(lists[i]);
        }
        return list;
    }

    /**
     * Turns the lager array into a list, which will be sorted according to the
     * given predicate.
     *
     * @param predicate sort criteria
     * @return sorted list
     */
//    public List<Artikel> getSorted(BiPredicate<Artikel, Artikel> predicate) {
//        list = new ArrayList<>();
//        Artikel backup;
//        for (int i = 0; i < lager.length - 1; i++) {
//            backup = lager[i];
//            for (int j = 1; j < lager.length - 1; j++) {
//                if (predicate.test(lager[i], lager[j]) == true) {
//                    lager[i] = lager[j];
//                    lager[j] = backup;
//                }
//            }
//            list.add(lager[i]);
//        }
//        return list;
//    }

    /**
     * Turns the lager array into a list, which will be filtered according to the
     * given predicate.
     *
     * @param predicate filter criteria
     * @return filtered list
     */
    public List<Artikel> filter(Predicate<Artikel> predicate) {
        List<Artikel> list = (List<Artikel>)storage.values().parallelStream()
                                                            .filter(predicate)
                                                            .collect(Collectors.toList());
        return list;
    }

    /**
     * Applys a given function to all articles in the lager array.
     *
     * @param function function to apply
     */
    public void applyToAllArticles(Consumer<Artikel> consumer) {
        storage.values().parallelStream()
                        .forEach(consumer);
    }

    /**
     * Applys a given function to some articles in the lager array.
     *
     * @param function  function to apply
     * @param predicate filter criteria
     */
    public void applyToSomeArticles(Predicate<Artikel> predicate, Consumer<Artikel> consumer) {
        storage.values().parallelStream()
                        .filter(predicate)
                        .forEach(consumer);
    }

    /**
     * Filters the array according to a given predicate and returns the filtered
     * objects as array, which is sorted according to a given Comparator.
     *
     * @param predicate  filter criteria
     * @param comparator sort criteria
     * @return filtered and sorted array
     */
    public Artikel[] getArticles(Predicate<Artikel> predicate, Comparator<Artikel> comparator) {
        List<Artikel> list = (List<Artikel>)storage.values().parallelStream()
                                                            .filter(predicate)
                                                            .sorted(comparator)
                                                            .collect(Collectors.toList());
        Artikel[] sortedArray = list.toArray(new Artikel[list.size()]);
        return sortedArray;
    }

    /**
     * Turns the lager array into a list, which will be filtered according to given
     * predicates.
     *
     * @param predicates array of multiple filter criterias
     * @return filtered list
     */
    @SuppressWarnings("unchecked")
    public Artikel[] filterAll(Predicate<Artikel>... predicates) {
        List<Artikel> list = (List<Artikel>)storage.values();
        for (int i = 0; i < predicates.length; i++) {
            list.parallelStream()
                .filter(predicates[i])
                .collect(Collectors.toList());
        }
        Artikel[] filteredArray = list.toArray(new Artikel[list.size()]);
        return filteredArray;
    }

    /**
     * Getter for an article object at a specific index point of the lager array
     *
     * @param index entered value which specifies which index point to show
     * @return article object at the specified point
     */
    public Artikel getArtikel(int index) {
        return storage.get(index);
    }

    /**
     * Getter for the article count
     *
     * @return article count
     */
    public int getArtikelAnzahl() {
        return storage.size();
    }

    /**
     * Definition of the toString method which is used to display a article object
     * as a string.
     *
     * @return configured string
     */
    @Override
    public String toString() {
    String s = storage.keySet().stream()
                               .map(key -> key + " ["+ storage.get(key) + "]")
//                                                        1. DELIMITER, 2. PREFIX, 3. SUFFIX
                               .collect(Collectors.joining("\n", "\n" + "--" + "\n", "\n" + "--"));
    return s;
    }

}
