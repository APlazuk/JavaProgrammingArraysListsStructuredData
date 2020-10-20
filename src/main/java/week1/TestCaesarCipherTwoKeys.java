package week1;

import edu.duke.FileResource;

public class TestCaesarCipherTwoKeys {

    private int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];

        for (int i = 0; i < message.length(); i++) {
            char c = Character.toLowerCase(message.charAt(i));
            int dex = alph.indexOf(c);

            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    private int maxIndex(int[] values) {
        int maxDex = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[maxDex]) {
                maxDex = i;
            }
        }
        return maxDex;
    }


    private String halfOfString(String message, int start) {

        StringBuilder sb = new StringBuilder();

        for (int i = start; i < message.length(); i++) {
            char c = message.charAt(i);

            if (i % 2 == start) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private int getKey(String s) {
        int[] freq = countLetters(s);
        int maxIndex = maxIndex(freq);
        int dkey = maxIndex - 4;

        if (maxIndex < 4) {
            dkey = 26 - (4 - maxIndex);
        }
        return dkey;
    }


    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();

        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(17, 3);
        String encrypt = cc.encrypt(message);
        System.out.println("Encrypted: " + encrypt);
        System.out.println("Decrypted: " + cc.decrypt(encrypt));

        String s = fr.asString();
        System.out.println("Decrypted with two keys: " + breakCaesarCipher(s));
    }

    public String breakCaesarCipher(String input) {
        String firstHalf = halfOfString(input.toLowerCase(), 0);
        String secondHalf = halfOfString(input.toLowerCase(), 1);

        int dkey1 = getKey(firstHalf);
        int dkey2 = getKey(secondHalf);


        System.out.println("first key: " + dkey1 + " ; " + " second key: " + dkey2);

        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(26 - dkey1, 26 - dkey2);
        return cc.encrypt(input);
    }
}
