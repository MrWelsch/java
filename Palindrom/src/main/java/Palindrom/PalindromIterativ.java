package Palindrom;

/**
 * PalindromIterativ class which checks a word is a palindrom.
 * Palindroms are words which are identical no matter which way they are read.
 * Author: Nico Welsch, Oliver Theobald
 * Version: 1.0
 */
public class PalindromIterativ implements Palindrom{
    //attributes
    /**
     *  method which checks if a entered String is a palindrome or not.
     *  @return true if it is a palindrome, false if not.
     */
    @Override
    public boolean istPalindrom(String wort){
        String reversed = "";
        // loop to reverse the word string
        for(int i = wort.length() - 1; i >= 0; i--){
            reversed += wort.charAt(i);
        }
        if(reversed.equalsIgnoreCase(wort)){
            return true;
        } else{
            return false;
        }
    }
}
