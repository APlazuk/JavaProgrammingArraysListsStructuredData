package week1;

import org.apache.commons.text.WordUtils;

public class CaesarCipher {

    public String encrypt(String input, int key) {
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

    public String encryptTwoKeys(String input, int key1, int key2) {
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
                        if (k == j) {
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

    public void testCaesar() {
        int key = 15;
//        FileResource fr = new FileResource();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26 - key);
        System.out.println(decrypted);
    }

    public void testWithTwoKeys() {
        int key1 = 21;
        int key2 = 8;
//        FileResource fr = new FileResource();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);
        String decrypted = encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        System.out.println(decrypted);
    }
}
