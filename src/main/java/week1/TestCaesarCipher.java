package week1;

import edu.duke.FileResource;

public class TestCaesarCipher {

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

    public String breakCaesarCipher(String input) {
        int[] freq = countLetters(input);
        int maxIndex = maxIndex(freq);

        int dkey = maxIndex - 4;
        if (maxIndex < 4) {
            dkey = 26 - (4 - maxIndex);
        }

        System.out.println("dkey: " + dkey);
        CaesarCipherOneKey cc = new CaesarCipherOneKey(26 - dkey);
        return cc.encrypt(input);
    }

    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();

        CaesarCipherOneKey cc = new CaesarCipherOneKey(18);
        String encrypt = cc.encrypt(message);
        System.out.println("Encrypted: " + encrypt);
        System.out.println("Decrypted: " + cc.decrypt(encrypt));


        message = fr.asString();
        System.out.println("Decrypted + key: " + breakCaesarCipher(message));
    }
}
