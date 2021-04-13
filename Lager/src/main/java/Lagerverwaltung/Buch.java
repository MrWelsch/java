package Lagerverwaltung;

/**
 * Buch class which extends the Article class
 * @author Nico Welsch, Oliver Theobald
 * @version 2.0
 */
public class Buch extends Artikel{

    private String titel;
    private String autor;
    private String verlag;

    /**
     * Constructor for the Buch subclass which is used to set values for all the
     * different attributes.
     * @param artikelNr
     * @param bestand
     * @param preis
     * @param autor
     * @param titel
     * @param verlag
     */
    public Buch(int artikelNr, int bestand, double preis, String autor, String titel, String verlag){
        super(artikelNr, "Medien", preis, bestand);
        check(autor != null && !autor.trim().isEmpty(), EMPTY_CONDITION);
        check(titel != null && !titel.trim().isEmpty(), EMPTY_CONDITION);
        check(verlag != null && !verlag.trim().isEmpty(), EMPTY_CONDITION);
        this.autor = autor;
        this.titel = titel;
        this.verlag = verlag;
    }

    /**
     * Getter to return the autor attribute
     * @return autor
     */
    public String getAutor(){
        return autor;
    }

    /**
     * Getter to return the titel attribute
     * @return titel
     */
    public String getTitel(){
        return titel;
    }

    /**
     * Getter to return the verlag attribute
     * @return verlag
     */
    public String getVerlag(){
        return verlag;
    }

    /**
     * Getter to return defined attributes as a String
     * @return autor and titel
     */
    public String getBeschreibung(){
        return autor + " : " + titel;
    }

    /** Definition of the toString method which is used to display a book object
     * as a string.
     * @return configured string
     */
    public String toString(){
        return super.toString() + " | " +
        "TITEL: "  + titel      + " | " +
        "AUTOR: "  + autor      + " | " +
        "VERLAG: " + verlag ;
    }

}
