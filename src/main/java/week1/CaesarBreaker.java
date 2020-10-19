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

    public static int maxIndex(int[] values){
        int maxDex = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[maxDex]){
                maxDex = i;
            }
        }
        return maxDex;
    }

    public static String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4; // 4 index = 'e' most frequent letter in English

        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }

        return cc.encrypt(encrypted, 26 - dkey);
    }

    public static void testDecrypt(){
        FileResource fileResource = new FileResource();
        String message = fileResource.asString();
        CaesarCipher caesarCipher = new CaesarCipher();

        String encrypt = caesarCipher.encrypt(message, 8);
        System.out.println(decrypt(encrypt) + " = " + message);
    }

    public static void main(String[] args) {
       testDecrypt();

    }

}
