package week4;

import edu.duke.FileResource;

import java.util.ArrayList;


public class VigenereBreaker {

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();

        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            char c = message.charAt(i);
            sb.append(c);
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] keys = new int[klength];
        ArrayList<String> slices = new ArrayList<>();

        for (int i = 0; i < klength; i++) {
            String sliceString = sliceString(encrypted, i, klength);
            slices.add(sliceString);
        }

        CaesarCracker cc = new CaesarCracker(mostCommon);

        for (int i = 0; i < slices.size(); i++) {
            int key = cc.getKey(slices.get(i));

            for (int j = 0; j < keys.length; j++) {
                keys[i] = key;
            }
        }

        return keys;
    }

    public void breakVigenere(int keyLength) {
        FileResource fr = new FileResource();
        String message = fr.asString();

        int[] keys = tryKeyLength(message, keyLength, 'e');

        VigenereCipher vc = new VigenereCipher(keys);
        System.out.println("Decrypted message: " + vc.decrypt(message));
    }
}
