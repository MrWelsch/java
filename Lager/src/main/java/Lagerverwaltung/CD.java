package Lagerverwaltung;

/**
 * CD class which extends the Article class
 * @author Nico Welsch, Oliver Theobald
 * @version 2.0
 */
public class CD extends Artikel{

    private String interpret;
    private String titel;
    private int anzahlTitel;

    /**
     * Constructor for the CD subclass which is used to set values for all the
     * different attributes.
     * @param artikelNr
     * @param bestand
     * @param preis
     * @param interpret
     * @param titel
     * @param anzahlTitel
     */
    public CD(int artikelNr, int bestand, double preis, String interpret, String titel, int anzahlTitel){
        super(artikelNr, "Medien", preis, bestand);
        check(interpret != null && !interpret.trim().isEmpty(), EMPTY_CONDITION);
        check(titel != null && !titel.trim().isEmpty(), EMPTY_CONDITION);
        check(anzahlTitel > 0, VALUE_CONDITION);
        this.interpret = interpret;
        this.titel = titel;
        this.anzahlTitel = anzahlTitel;
    }

    /**
     * Getter for the interpret attribute
     * @return interpret
     */
    public String getInterpret(){
        return interpret;
    }

    /**
     * Getter for the titel attribute
     * @return titel
     */
    public String getTitel(){
        return titel;
    }

    /**
     * Getter for the anzahlTitel attribute
     * @return anzahlTitel
     */
    public int getAnzahlTitel(){
        return anzahlTitel;
    }

    /**
     * Getter to return the attributes as a String
     * @return interpret and titel
     */
    public String getBeschreibung(){
        return interpret + " : " + titel;
    }

    /** Definition of the toString method which is used to display a cd object
     * as a string.
     * @return configured string
     */
    public String toString(){
        return super.toString()       + " | " +
        "TITEL: "       + titel       + " | " +
        "INTERPRET: "   + interpret   + " | " +
        "TITELANZAHL: " + anzahlTitel;
    }

}
