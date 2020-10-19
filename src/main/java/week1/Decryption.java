package week1;


public class Decryption {

    public static void countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];

        for (int i = 0; i < message.length(); i++) {
            char c = Character.toLowerCase(message.charAt(i));
            int dex = alph.indexOf(c);

            if (dex != -1) {
                counts[dex] += 1;
            }
        }
    }

    public static void main(String[] args) {
        countLetters("aazb");

    }

}
