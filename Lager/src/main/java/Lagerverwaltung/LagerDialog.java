package Lagerverwaltung;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ArtikelDialog class to manually test the previously programmed Artikel class
 * @author  Nico Welsch, Oliver Theobald
 * @version 3.0
 */
public class LagerDialog {

    private Artikel artikel;
    private Lager lager;
    private Scanner input;

    private static final int ADD_ARTICLE          = 1;
    private static final int DELETE_ARTICLE       = 2;
    private static final int ADD                  = 3;
    private static final int REMOVE               = 4;
    private static final int CHANGE_ALL_PRICES    = 5;
    private static final int GET_ARTICLE          = 6;
    private static final int GET_ARTICLE_COUNT    = 7;
    private static final int BESTANDSLISTE        = 9;
    private static final int END                  = 0;

    private static final String MAIN_MENU =                                                "\n" +
                                            "--"                                         + "\n" +
                                            ADD_ARTICLE          + ": NEW ARTICLE      " + "\n" +
                                            DELETE_ARTICLE       + ": DELETE ARTICLE   " + "\n" +
                                            ADD                  + ": ADD STOCK        " + "\n" +
                                            REMOVE               + ": REMOVE STOCK     " + "\n" +
                                            CHANGE_ALL_PRICES    + ": CHANGE ALL PRICES" + "\n" +
                                            GET_ARTICLE          + ": SHOW ARTICLE     " + "\n" +
                                            GET_ARTICLE_COUNT    + ": ARTICLE COUNT    " + "\n" +
                                            BESTANDSLISTE        + ": STORAGE LIST     " + "\n" +
                                            END                  + ": TERMINATE        " + "\n" +
                                            "--"                                         + "\n" +
                                            "-> "                ;

    private static final String ARTICLE_NUMBER = "\n" + "ARTICLE NUMBER: ";
    private static final String PRICE          = "PRICE: ";
    private static final String STOCK          = "STOCK: ";
    private static final String TITLE          = "\n" + "TITLE: ";
    private static final String AUTHOR         = "\n" + "AUTHOR: ";
    private static final String INTERPRET      = "\n" + "INTERPRET: ";
    private static final String DURATION       = "\n" + "DURATION: ";
    private static final String TITLE_COUNT    = "TITLE_COUNT: ";
    private static final String YEAR           = "YEAR: ";
    private static final String PUBLISHER      = "PUBLISHER: ";

    private static final String ONE   = "1";
    private static final String TWO   = "2";
    private static final String THREE = "3";

    public LagerDialog(){
        input = new Scanner(System.in);
        lager = new Lager();
    }

     /**
     * Main loop for the dialogue-programme which trys to execute the user
     * interface and the assigned functions as long as the programme
     * doesn't catch an exception.
     * If it catches an exception the programme will print out the exception
     * so that the user can see what went wrong.
     */
    public void start(){
        int funktion = -1;
        while(funktion != END){
            try{
                funktion = einleseFunktion();
                ausfuehrenFunktion(funktion);
            }
            catch(IllegalArgumentException e){
                System.out.println(e);
            }
            catch(InputMismatchException e){
                System.out.println(e);
                input.nextLine();
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
    }

    /**
     * Method to create an user interface which is able to read the user input
     * and execute the assigned function.
     */
    private int einleseFunktion(){
        int funktion;
        System.out.print(MAIN_MENU);
        funktion = input.nextInt();
        input.nextLine();
        return funktion;
    }

    /**
     * Method to assign each expression a method which runs if the function is
     * triggered.
     * If none of the functions is triggered the programme will print out:
     * "False Function".
     * After executing the assigned method the programme will then print out
     * the object.
     */
    private void ausfuehrenFunktion(int funktion){
        if (funktion == ADD_ARTICLE){
            lager.legeAnArtikel(artikelAnlegen());
        }
        else if (funktion == DELETE_ARTICLE){
            lager.entferneArtikel(einleseArtikelNr());
        }
        else if (funktion == ADD){
            lager.bucheZugang(einleseArtikelNr(),einleseBetrag());
        }
        else if (funktion == REMOVE){
            lager.bucheAbgang(einleseArtikelNr(),einleseBetrag());
        }
        else if (funktion == CHANGE_ALL_PRICES){
            lager.aenderePreisAllerArtikel(einleseProzent());
        }
        else if (funktion == GET_ARTICLE){
            System.out.println(lager.getArtikel(einleseIndex()));
        }
        else if (funktion == GET_ARTICLE_COUNT){
            System.out.println(lager.getArtikelAnzahl());
        }
        else if (funktion == BESTANDSLISTE){
            System.out.println(lager);
        }
        else if (funktion == END){
            System.out.println("TERMINATED");
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * This method enables the user to create an article with described
     * attributes.
     * It will ask the user to enter values for each of the defined attributes
     * and use the constructor of the Article-class to create an object.
     * @return created object
     */
    private Artikel artikelAnlegen(){
        int artikelNr, anzahlTitel, spieldauer, jahr;
        int bestand = 0;
        double preis;
        String bezeichnung, titel, interpret, author, verlag;
        // input
        System.out.print(ARTICLE_NUMBER);
        artikelNr = input.nextInt();
        input.nextLine();
        System.out.print(PRICE);
        preis = input.nextDouble();
        // give user the option to create an article without stock
        input.nextLine();
        System.out.print(STOCK);
        bestand = input.nextInt();
        input.nextLine();
        // define which kind of article to create
        System.out.print("\n" + "SPECIFIC TYPE" + "\n" + "(1) YES" + "\n" + "(2) NO" + "\n" + "-> ");
        String userInput = input.nextLine();
        if(userInput.equals(ONE)){
            System.out.print(TITLE);
            titel = input.nextLine();
            System.out.print("\n" + "CHOOSE" + "\n" + "(1) CD" + "\n" + "(2) VIDEO" + "\n" + "(3) BOOK" + "\n" + "-> ");
            userInput = input.nextLine();
            // cd
            if(userInput.equals(ONE)){
                System.out.print(INTERPRET);
                interpret = input.nextLine();
                System.out.print(TITLE_COUNT);
                anzahlTitel = input.nextInt();
                artikel = new CD(artikelNr, bestand, preis, interpret, titel, anzahlTitel);
            }
            // video
            else if(userInput.equals(TWO)){
                System.out.print(DURATION);
                spieldauer = input.nextInt();
                input.nextLine();
                System.out.print(YEAR);
                jahr = input.nextInt();
                artikel = new Video(artikelNr, bestand, preis, titel, spieldauer, jahr);
            }
            // buch
            else if(userInput.equals(THREE)){
                System.out.print(AUTHOR);
                author = input.nextLine();
                System.out.print(PUBLISHER);
                verlag = input.nextLine();
                artikel = new Buch(artikelNr, bestand, preis, author, titel, verlag);
            }
        }
        // standard article
        else if(userInput.equals(TWO)){
            System.out.print(TITLE);
            bezeichnung = input.nextLine();
            artikel = new Artikel(artikelNr, bezeichnung, preis, bestand);
        }
        else{
            throw new InputMismatchException();
        }
    return artikel;
    }

    /**
     * Method which lets the user enter an integer-value which can be used in
     * methods previously defined in the Article.class
     * @return entered integer value
     */
    private int einleseBetrag(){
        System.out.print("\n" + "AMOUNT: ");
        return input.nextInt();
    }

    /**
     * Method which lets the user enter an integer-value which can be used in
     * methods previously defined in the Article.class
     * @return entered integer value
     */
    private int einleseIndex(){
        System.out.print(ARTICLE_NUMBER);
        return input.nextInt();
    }

    /**
     * Method which lets the user enter an integer-value which can be used in
     * methods previously defined in the Article.class
     * @return entered integer value
     */
    private int einleseArtikelNr(){
        System.out.print(ARTICLE_NUMBER);
        return input.nextInt();
    }

    /**
     * Method which lets the user enter a double-value which can be used in
     * methods previously defined in the Article.class
     * @return entered double value
     */
    private double einleseProzent(){
        double prozent;
        System.out.println("\n" + "ENTER A PERCENTAGE (+,-): ");
        prozent = input.nextDouble();
        return prozent;
    }

    /**
     * Main method to create the dialogue-object.
     */
    public static void main (String[]args){
       new LagerDialog().start();
    }

}
