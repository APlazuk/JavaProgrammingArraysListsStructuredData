package week1;

import org.apache.commons.text.WordUtils;

public class CaesarCipherTwoKeys {

    private int scdKey;
    private int firstKey;
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;

    public CaesarCipherTwoKeys(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        firstKey = key1;
        scdKey = key2;
    }


    public String encrypt(String input) {

        CaesarCipherOneKey ccOneKey = new CaesarCipherOneKey(firstKey);
        String encrypt = ccOneKey.encrypt(input).toUpperCase();

        StringBuilder sb = new StringBuilder(input.toUpperCase());

        for (int i = 1; i < sb.length(); i += 2) {

            char currChar = sb.charAt(i);
            int idx = alphabet.indexOf(currChar);

            if (idx != -1) {
                char newChar = shiftedAlphabet2.charAt(idx);
                sb.setCharAt(i, newChar);
            }
        }

        for (int i = 0; i < sb.length(); i++) {
            for (int j = 0; j < input.length(); j++) {
                char currChar = sb.charAt(j);
                char inputChar = input.toUpperCase().charAt(j);

                if (currChar == inputChar) {
                    for (int k = 0; k < encrypt.length(); k++) {
                        char firstChar = encrypt.charAt(k);

                        if (k == j) {
                            sb.setCharAt(j, firstChar);
                        }
                    }
                }
            }
        }
        String s = sb.toString();
        return WordUtils.capitalizeFully(s);
    }

    public String encrypt2(String input) {

        StringBuilder encrypted = new StringBuilder(input.toUpperCase());
        String encrypt = setChar(encrypted);

        return WordUtils.capitalizeFully(encrypt);
    }

    private String setChar(StringBuilder sb) {
        char newChar;
        for (int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            int idx = alphabet.indexOf(currChar);

            if (idx != -1) {
                if (i % 2 == 0) {
                    newChar = shiftedAlphabet1.charAt(idx);
                } else {
                    newChar = shiftedAlphabet2.charAt(idx);
                }
                sb.setCharAt(i, newChar);
            }
        }
        return sb.toString();
    }

    public String decrypt(String input) {
        CaesarCipherTwoKeys ccTwo = new CaesarCipherTwoKeys(26 - firstKey, 26 - scdKey);
        return ccTwo.encrypt(input);
    }

    public String decrypt2(String input) {
        CaesarCipherTwoKeys ccTwo = new CaesarCipherTwoKeys(26 - firstKey, 26 - scdKey);
        return ccTwo.encrypt2(input);
    }
}
