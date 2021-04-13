package Palindrom;

/**
 * PalindromTest class which checks a word is a palindrom.
 * Palindroms are words which are identical no matter which way they are read.
 * Author: Nico Welsch, Oliver Theobald
 * Version: 1.0
 */
public class PalindromRekursiv implements Palindrom{
    /**
     *  method which checks if a entered String is a palindrome or not.
     *  @return true if it is a palindrome, false if not.
     */
    @Override
    public boolean istPalindrom(String wort){
        if(wort.length() == 0 || wort.length() == 1){
            return true;
        } else if(wort.charAt(0) == wort.charAt(wort.length()-1)){
            // check for first and last char of String:
            // if they are same then do the same thing for a substring
            // with first and last char removed. and carry on this
            // until you string completes or condition fails
            return istPalindrom(wort.substring(1, wort.length()-1));
        }
        return false;
    }
}
