package week1;


import edu.duke.FileResource;

public class CaesarBreaker {

    public static int[] countLetters(String message) {
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

    public static int maxIndex(int[] values) {
        int maxDex = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[maxDex]) {
                maxDex = i;
            }
        }
        return maxDex;
    }

    public static String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4; // 4 index = 'e' most frequent letter in English

        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }

        return cc.encrypt(encrypted, 26 - dkey);
    }

    public static String halfOfString(String message, int start) {

        StringBuilder sb = new StringBuilder();

        for (int i = start; i < message.length(); i++) {
            char c = message.charAt(i);

            if (i % 2 == start) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static int getKey(String s) {
        int[] freq = countLetters(s);
        int maxDex = maxIndex(freq);

        return maxDex;
    }

    public static String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);

        int maxDex1 = getKey(firstHalf);
        int maxDex2 = getKey(secondHalf);

        int dkey1 = maxDex1 - 4;
        int dkey2 = maxDex2 - 4;

        if (maxDex1 < 4) {
            dkey1 = 26 - (4 - maxDex1);
        }

        if (maxDex2 < 4) {
            dkey2 = 26 - (4 - maxDex2);
        }

        System.out.println("first key: " + dkey1 + " ; " + " second key: " + dkey2);

        return cc.encryptTwoKeys(encrypted, 26 - dkey1, 26 - dkey2);
    }

    public static void testDecrypt() {
        FileResource fileResource = new FileResource();
        String message = fileResource.asString();
        CaesarCipher caesarCipher = new CaesarCipher();

        String encrypt = caesarCipher.encrypt(message, 8);
        System.out.println(decrypt(encrypt) + " = " + message);
    }

    public static void testDecryptTwoKeys() {
        FileResource fileResource = new FileResource();
//        String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        String message = fileResource.asString();
        CaesarCipher caesarCipher = new CaesarCipher();


        System.out.println("decryption: " + decryptTwoKeys(message) + " = " + message);

    }

    public static void main(String[] args) {
//       testDecrypt();
//        System.out.println(halfOfString("Qbkm Zgis", 1));
        testDecryptTwoKeys();
    }

}
