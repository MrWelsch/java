package Palindrom;

/**
 * MathFunctionsDialog class to test the MathFunctions class interactively.
 * Author: Nico Welsch, Oliver Theobald
 * Version: 2.0
 */
import java.util.*;
public class MathFunctionsDialog{
    private Scanner input = new Scanner(System.in);
    //class constants
    private static final int TEILSUMME          = 1;
    private static final int ISBN10             = 2;
    private static final int NULLSTELLEN        = 3;
    private static final int SUMME_VON_POTENZEN = 4;
    private static final int REIHENSUMME        = 5;
    private static final int GGT                = 6;
    private static final int ENDE               = 0;
    /**
     * main loop for the dialogue-programme which trys to execute the user
     * interface and the assigned functions as long as the programme doesn't
     * catch an exceptionm
     * If it catches an exception the programme will print out the exception
     * so that the user can see what went wrong.
     */
    public void start(){
        int funktion = -1;
        while(funktion != ENDE){
            try {
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
                System.out.println("Ausnahme gefangen" + e);
                e.printStackTrace(System.out);
            }
        }
    }
     /**
     * method to create an user interface which is able to read the user input.
     * @return executes the assigned function
     */
    private int einleseFunktion(){
        int funktion;
        System.out.println(                                                 "\n" +
                                                   "###################"  + "\n" +
                           TEILSUMME             + ":teilsumme "          + "\n" +
                           ISBN10                + ":isbn10 "             + "\n" +
                           NULLSTELLEN           + ":nullstellen "        + "\n" +
                           SUMME_VON_POTENZEN    + ":summe von potenzen " + "\n" +
                           REIHENSUMME           + ":reihensumme "        + "\n" +
                           GGT                   + ":ggt "                + "\n" +
                           ENDE                  + ":beenden -> "         + "\n" +
                                                   "###################"  + "\n"
                           );
        funktion = input.nextInt();
        input.nextLine();
        return funktion;
    }
     /**
     * method to assign each expression a method which runs if the function is
     * triggered.
     * If none of the functions is triggered the programme will print out:
     * "False Function".
     */
    private void ausfuehrenFunktion(int funktion){
        if(funktion == TEILSUMME){
            System.out.println(
                               MathFunctions.berechneTeilersumme(einleseTeilsumme())
                               );
        }
        else if(funktion == ISBN10){
            System.out.println(
                               MathFunctions.berechneChecksummeIsbn(einleseIsbn())
                               );
        }
        else if(funktion == NULLSTELLEN){
            System.out.println(
                               MathFunctions.berechneNullstellen(einleseP(),einleseQ())
                               );
        }
        else if(funktion == SUMME_VON_POTENZEN){
            System.out.println(
                               MathFunctions.istSummeVonPotenzen(einleseIstSummeVonPotenzen())
                               );
        }
        else if(funktion == REIHENSUMME){
            System.out.println(
                               MathFunctions.berechneReihensumme(einleseReihensummeN(),einleseReihensummeX())
                               );
        }
        else if(funktion == GGT){
            System.out.println("Der GgT der beiden Zahlen lautet: " + "\n" +
                               MathFunctions.berechneGgt(einleseLong(), einleseLong())
                               );
        }
        else if(funktion == ENDE){
            System.out.println("Programmende");
        }
        else{
            System.out.println("Falsche Funktion!");
        }
    }
     /**
     * method which lets the user enter a long value which can be used in
     * methods previously defined in the MathFunctions class.
     * @return returns the entered long value
     */
    private long einleseTeilsumme(){
        System.out.print("Teilsumme berechnen von: ");
        return input.nextLong();
    }
     /**
     * method which lets the user enter a long value which can be used in
     * methods previously defined in the MathFunctions class.
     * @return returns the entered long value
     */
    private long einleseIsbn(){
        System.out.print("Bitte geben Sie die ersten neun Stellen der ISBN-Nummer ein: ");
        return input.nextLong();
    }
    /**
     * method which lets the user enter a long value which can be used in
     * methods previously defined in the MathFunctions class.
     * @return returns the entered long value
     */
    private long einleseIstSummeVonPotenzen(){
        System.out.print("Bitte geben Sie einen Wert n ein: ");
        return input.nextLong();
    }
    /**
     * method which lets the user enter an int value which can be used in
     * methods previously defined in the MathFunctions class.
     * @return returns the entered int value
     */
    private int einleseReihensummeN(){
        int n;
        System.out.print("n: ");
        n = input.nextInt();
        return n;
    }
    /**
     * method which lets the user enter a double value for the x-variable
     * which is used in the berechneReihensumme() method previously defined in
     * the MathFunctions class.
     * @return returns the entered double value
     */
    private double einleseReihensummeX(){
        double x;
        System.out.println("x: ");
        x = input.nextDouble();
        return x;
    }
     /**
     * method which lets the user enter a double value for the p-variable
     * which is used in the berechneNullstellen() method previously defined in
     * the MathFunctions class.
     * @return [Returns the entered double value]
     */
    private double einleseP(){
        double p;
        System.out.println("p: ");
        p = input.nextDouble();
        return p;
    }
     /**
     * method which lets the user enter a double value for the q-variable
     * which is used in the berechneNullstellen() method previously defined in
     * the MathFunctions class.
     * @return returns the entered double value
     */
    private double einleseQ(){
        double q;
        System.out.println("q: ");
        q = input.nextDouble();
        return q;
    }
    /**
     * method which lets the user enter a long value which is used in the
     * berechneGgt() method previously defined in the MAthFunctions class.
     * @return returns the entered long value
     * */
    private long einleseLong(){
        long l;
        System.out.println("Bitte geben Sie einen long-Wert ein: ");
        l = input.nextLong();
        return l;
    }
     /**
     * Main method to create the dialogue object.
     */
    public static void main(String[]args){
        new MathFunctionsDialog().start();
    }
}
