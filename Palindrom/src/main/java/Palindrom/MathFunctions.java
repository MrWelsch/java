package Palindrom;

/**
 * MathFunctions class to solve math problems such as calculating the test
 * figure of an isbn10, a Teilsumme, zeros, if an entered value is a sum of
 * powers and an array sum.
 * Reihensumme.
 * Author: Nico Welsch, Oliver Theobald
 * Version: 2.0
 */
public class MathFunctions {
    //class constants
    private static final int    UNTER_NEUNSTELLIG  = 100000000;
    private static final int    UEBER_NEUNSTELLIG  = 999999999;
    private static final double DOUBLE_TOLERANZ    = 1e-10;
    /**
     * calculate the Teilersumme
     * @param zahl entered figure
     * @return sum of the factors
     */
    public static long berechneTeilersumme(long zahl) {
        check(zahl <= 0, "The figure must be > 0");
        //definition of the variable sum
        long sum = zahl;
        for(long i = 1; i <= zahl / 2; i++) {
            if(zahl % i == 0) {
                //aggregates i if it is a whole factor of the variable zahl to the sum
                sum += i;
            }
        }
        return sum;
    }
    /**
     * method to calculate the ISBN test-figure
     * @param  isbn entered variable which is used to calculate the variable
     * ziffer
     * @return variable ziffer as a String
     */
    public static String berechneChecksummeIsbn(long isbn) {
        long ziffer = 0;
        check(isbn < UNTER_NEUNSTELLIG, "The entered ISBN-number is invalid");
        check(isbn > UEBER_NEUNSTELLIG, "The entered ISBN-number is invalid");
        while(isbn > 0) {
        for(long i = 9; i > 0; i--) {

                //long figure mod 10 singles out the figures of the entered isbn number from rigth to left
                //(if mod is used by a data type, same datatype will be returned
                // so for example long mod will return as a long number)
                //isbn divided by 10 jumps to the next figure of the entered isbn number
                //long zahl (Nachkommazahlen werden abgeschnitten)
                ziffer += (isbn % 10) * i;
                isbn = isbn / 10;
            }
        }
        ziffer = ziffer % 11;
        if(ziffer == 10) {
            return String.valueOf("X");
        }
        return String.valueOf(ziffer);
    }
    /**
     * method to calculate zeros and differentiate between complex, double and
     * two different zeros
     * @param  p variable to calculate the zeros
     * @param  q variable to calculate the zeros
     * @return zeros x1 and x2 as a String
     */
    public static String berechneNullstellen(double p, double q) {
        //definition of variables
        String nullstellen = "";
        double threshold = DOUBLE_TOLERANZ;
        double x1, x2;
        double pHalbe = p/2.0;
        double pqWurzel = (Math.pow(pHalbe,2.0))-q;
        if(Math.abs(pqWurzel) <= threshold ) {
        pqWurzel = 0.0;
        }
        if(pqWurzel < 0.0) {
            nullstellen = "Komplexe Nullstellen";
        }
        else {
        //check if the margin of error regarding calculations with the double type is in the previously
        //defined threshold, if not set the variable pqWurzel to 0.0
            x1 = -pHalbe + Math.sqrt(pqWurzel);
            x2 = -pHalbe - Math.sqrt(pqWurzel);
            if(Math.sqrt(pqWurzel) == 0.0) {
            nullstellen = "Doppelte Nullstelle: " + " " + x1;
            }
            else {
            nullstellen = "Zwei Nullstellen: " + " " + x1 + " " + " | " + " " + x2;
            }
        }
    return nullstellen;
    }
    /**
     * method to determine if an entered value is a sum of powers
     * @param  n value to check
     * @return   returns true if it is a value and false if not
     */
    public static boolean istSummeVonPotenzen(long n) {
        check(n <= 0, "Invalid n entered");
        for(long a = 1; a * a * a * a < n; a++) {
            long aHochVier = a * a * a * a;
            for(long b = 1; b * b * b < n; b++) {
                long bKubik = b * b * b;
                for(long c = 1; c * c < n; c++) {
                    long cQuadrat = c * c;
                    if(n == aHochVier + bKubik + cQuadrat) {
                    return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * method to calculate the array sum
     * @param  n value to determine how many times the loop is run
     * @param  x value to calculate the array sum
     * @return   returns the calculated array sum
     */
    public static double berechneReihensumme(int n, double x) {
        check(x == 0.0, "Invalid x entered");
        check(n <= 0, "Invalid n entered");
        double zaehlerTerm = (x-1);
        double zaehler = zaehlerTerm;
        double nennerTerm = x;
        double nenner = nennerTerm;
        double bruch;
        double summe = zaehler / nenner;
        for(int i = 2; i <= n; i++) {
            //multiply (x-1) with itself each time the loop runs through and save the result in the zaehler-variable
            //this is the same as (x-1)^i
            zaehler *= zaehlerTerm;
            //multiply x with itself each time the loop runs through and save the result in the nennerTerm-variable
            //this is the same as x^i
            nennerTerm *= x;
            nenner = nennerTerm * i;
            bruch = zaehler / nenner;
            //adds the result of the bruch variable to the sum.
            //the result of the first step, i=1, is already stored in the sum.
            summe += bruch;
        }
        return summe;
    }
    /**
     * method to calculate the ggT of two numbers recursively
     * @param a
     * @param b
     * */
    public static long berechneGgt(long a, long b){
        check(a < 0, "Invalid Input: Positive Long expected");
        check(b < 0, "Invalid Input: Positive Long expected");
        if(b == 0){
            return a;
        }
        else{
            return berechneGgt(b, a % b);
        }
    }
    /**
     * check method to set a condition which, if not met, throws an
     * IllegalArgumentException()
     */
   public static void check(boolean bedingung, String msg) {
      if (bedingung)
      throw new IllegalArgumentException(msg);
    }
}
