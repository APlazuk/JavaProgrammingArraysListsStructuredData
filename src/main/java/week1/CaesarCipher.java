package week1;

import edu.duke.FileResource;
import org.apache.commons.text.WordUtils;

public class CaesarCipher {

    public static void main(String[] args) {

//        testCaesar();
        testWithTwoKeys();
    }

    public static String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input.toUpperCase());
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        //Count from 0 to < length of encrypted, (call it i)
        for (int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if (idx != -1) {
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
        }

        //Otherwise: do nothing
        //Your answer is the String inside of encrypted
        String s = encrypted.toString();
        return WordUtils.capitalizeFully(s);
    }

    public static String encryptTwoKeys(String input, int key1, int key2) {
        String encrypt = encrypt(input, key1).toUpperCase();

        StringBuilder encrypted = new StringBuilder(input.toUpperCase());
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key2) + alphabet.substring(0, key2);

        for (int i = 1; i < encrypted.length(); i += 2) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);

            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }


        for (int i = 0; i < encrypted.length(); i++) {

            for (int j = 0; j < input.length(); j++) {
                char inputChar = input.toUpperCase().charAt(j);
                char currChar = encrypted.charAt(j);

                if (currChar == inputChar) {
                    for (int k = 0; k < encrypt.length(); k++) {
                        char firstChar = encrypt.charAt(k);
                        if (k == j){
                            encrypted.setCharAt(j, firstChar);
                            break;
                        }
                    }
                }
            }
        }
        String s = encrypted.toString();

        return WordUtils.capitalizeFully(s);
    }

    public static void testCaesar() {
        int key = 15;
//        FileResource fr = new FileResource();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26 - key);
        System.out.println(decrypted);
    }

    public static void testWithTwoKeys() {
        int key1 = 8;
        int key2 = 21;
//        FileResource fr = new FileResource();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);
        String decrypted = encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        System.out.println(decrypted);
    }
}
