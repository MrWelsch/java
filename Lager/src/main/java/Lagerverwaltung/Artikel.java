package Lagerverwaltung;

import java.text.DecimalFormat;

/**
 * Article class for simple inventory management
 * @author Nico Welsch, Oliver Theobald
 * @version 2.0
 */
public class Artikel {

    private int artikelNr;
    private String bezeichnung;
    private double preis;
    private int bestand;

    private DecimalFormat df = new DecimalFormat("#.00");

    private static double ONE_HUNDRED = 100.0;

    public static String FOUR_DIGIT_CONDITION = "\n" + "ARTICLE NUMBER AND YEAR MUST BE FOUR DIGIT";
    public static String STOCK_CONDITION          = "\n" + "STOCK MUST BE >= 0";
    public static String VALUE_CONDITION          = "\n" + "VALUE MUST BE > 0";
    public static String EMPTY_CONDITION          = "\n" + "CANNOT BE EMPTY";

    /**
     * Constructor for the article class which is used to set values for all the
     * different attributes defined in the Artikel class.
     * @param artikelNr muss 4-stellig und >0 sein.
     * @param bestand !<0
     */
    public Artikel(int artikelNr, String bezeichnung, double preis, int bestand){
        check(bestand >= 0, STOCK_CONDITION);
        check(artikelNr >= 1000 && artikelNr < 10000, FOUR_DIGIT_CONDITION);
        check(preis > 0, VALUE_CONDITION);
        this.artikelNr = artikelNr;
        //attribute is defined in the set-method.
        setBezeichnung(bezeichnung);
        this.preis = preis;
        this.bestand = bestand;
    }

    /**
     * Constructor for the article class which is used to set values for all the
     * different attributes defined in the Artikel class except the bestand
     * attribute.
     */
    public Artikel(int artikelNr, String bezeichnung, double preis){
        this(artikelNr, bezeichnung, preis, 0);
    }

    /**
     * method which is used to add an amount of articles to the bestand
     * attribute.
     * @param menge > 0
     */
    public void bucheZugang(int menge){
        check(menge > 0, VALUE_CONDITION);
        bestand += menge;
    }

    /**
     * method which is used to substract an amount of articles from the bestand
     * attribute.
     * @param bestand !<0
     */
    public void bucheAbgang(int menge){
        check(bestand - menge >= 0, STOCK_CONDITION);
        check(menge > 0, VALUE_CONDITION);
        bestand -= menge;
    }

    /**
     * method to change the price variable of an article
     * @param prozent double value to change the price
     */
    public void aenderePreis(double prozent){
        check(prozent > -ONE_HUNDRED, VALUE_CONDITION);
        if(prozent < 0.0){
                preis = preis - Math.abs(preis * prozent/ONE_HUNDRED);
            }
            if(prozent > 0.0){
                preis = preis + preis * prozent/ONE_HUNDRED;
            }
    }

    /**
     * Getter for artikelnr to actually get the artikelnr "printed" out.
     */
    public int getArtikelNr(){
        return artikelNr;
    }

    /**
     * Setter for bezeichnung.
     * @param bezeichnung !=null
     */
    public void setBezeichnung(String bezeichnung){
        check(bezeichnung != null && !bezeichnung.trim().isEmpty(), EMPTY_CONDITION);
        this.bezeichnung = bezeichnung.trim();
    }

    /**
     * Getter for bezeichnung to actually get the bezeichnung "printed" out.
     */
    public String getBezeichnung(){
        return bezeichnung;
    }

    /**
     * setter for preis
     * @param preis !<= 0
     */
    public void setPreis(double preis){
        check(preis > 0, VALUE_CONDITION);
        this.preis = preis;
    }

    /**
     * Getter for bestand to actually get the preis "printed" out.
     */
    public double getPreis(){
        return preis;
    }

    /**
     * Getter for bestand to actually get the bestand "printed" out.
     */
    public int getBestand(){
        return bestand;
    }

    /**
     * setter for stock
     * @param stock !<= 0
     */
    public void setBestand(int bestand){
        check(bestand > 0, STOCK_CONDITION);
        this.bestand = bestand;
    }

    /**
     * Definition of the toString method which is used to display a article object
     * as a string.
     * @return configured string
     */
    public String toString(){
        return (
                "ARTIKEL: "      + artikelNr        + " | " +
                "BEZEICHNUNG: "  + bezeichnung      + " | " +
                "BESTAND: "      + bestand          + " | " +
                "PREIS: "        + df.format(preis) + " EUR"
                );
    }

    /**
     * check-method to set a condition which, if not met, throws an
     * IllegalArgumentException().
     */
    public static void check(boolean bedingung, String msg){
        if (!bedingung)
        throw new IllegalArgumentException(msg);
    }

}
