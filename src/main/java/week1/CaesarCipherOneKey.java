package week1;

import org.apache.commons.text.WordUtils;


public class CaesarCipherOneKey {

    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipherOneKey(int key) {

        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +
                alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt(String input) {

        StringBuilder encrypted = new StringBuilder(input.toUpperCase());

        for (int i = 0; i < encrypted.length(); i++) {
            char c = encrypted.charAt(i);
            int idx = alphabet.indexOf(c);

            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        String s = encrypted.toString();
        return WordUtils.capitalizeFully(s);
    }

    public String decrypt(String input) {
        CaesarCipherOneKey cc = new CaesarCipherOneKey(26 - mainKey);
        return cc.encrypt(input);
    }
}
