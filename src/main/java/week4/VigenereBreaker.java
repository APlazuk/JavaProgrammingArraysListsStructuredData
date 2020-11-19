package week4;

import edu.duke.FileResource;

import java.util.*;


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

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String message = fr.asString();

        FileResource fileResource = new FileResource();
        HashSet<String> dictionary = readDictionary(fileResource);

        System.out.println("Decrypted message: " + breakForLanguage(message, dictionary));
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<>();

        for (String line : fr.lines()) {
            String word = line.toLowerCase();
            dictionary.add(word);
        }
        return dictionary;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;

        for (String word : message.split("\\W")) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        ArrayList<Integer> max = new ArrayList<>();
        int keyLength = 0;
        int value = 0;

        for (int i = 1; i < 100; i++) {
            int[] keys = tryKeyLength(encrypted, i, 'e');
            VigenereCipher vc = new VigenereCipher(keys);

            String decrypt = vc.decrypt(encrypted);
            max.add(countWords(decrypt, dictionary));
            Integer maxMatch = max.stream().max(Comparator.comparing(Integer::intValue)).orElseThrow(NoSuchElementException::new);

            if (maxMatch > value) {
                value = maxMatch;
                keyLength = i;
            }
        }

        Integer maxMatch = max.stream().max(Comparator.comparing(Integer::intValue)).orElseThrow(NoSuchElementException::new);
        System.out.println(maxMatch);

        int[] rightKeys = tryKeyLength(encrypted, keyLength, 'e');
        VigenereCipher cipher = new VigenereCipher(rightKeys);

        System.out.println(Arrays.toString(rightKeys));
        System.out.println(keyLength);

        return cipher.decrypt(encrypted);
    }
}
