package Lagerverwaltung;

/**
 * Video class which extends the Article class
 * @author Nico Welsch, Oliver Theobald
 * @version 2.0
 */
public class Video extends Artikel{

    private String titel;
    private int spieldauer;
    private int jahr;

    /**
     * Constructor for the Video subclass which is used to set values for all
     * the different attributes.
     * @param artikelNr
     * @param bestand
     * @param preis
     * @param titel
     * @param spieldauer
     * @param jahr
     */
    public Video(int artikelNr, int bestand, double preis, String titel, int spieldauer, int jahr){
        super(artikelNr, "Medien", preis, bestand);
        check(titel != null && !titel.trim().isEmpty(), EMPTY_CONDITION);
        check(jahr >= 1000 && jahr < 10000, FOUR_DIGIT_CONDITION);
        check(spieldauer > 0, VALUE_CONDITION);
        this.titel = titel;
        this.spieldauer = spieldauer;
        this.jahr = jahr;
    }

    /**
     * Getter for the titel attribute
     * @return titel
     */
    public String getTitel(){
        return titel;
    }

    /**
     * Getter for the spieldauer attribute
     * @return spieldauer
     */
    public int getSpieldauer(){
        return spieldauer;
    }

    /**
     * Getter for the jahr attribute
     * @return jahr
     */
    public int getJahr(){
        return jahr;
    }

    /**
     * Getter to return the attributes as a String
     * @return titel
     */
    public String getBeschreibung(){
        return titel;
    }

    /**
    * Definition of the toString method which is used to display a video object
    * as a string.
    * @return configured string
    */
    public String toString(){
        return super.toString()     +          " | " +
        "TITEL: "      + titel      +          " | " +
        "SPIELDAUER: " + spieldauer + " min" + " | " +
        "JAHR: "       + jahr ;
    }

}
