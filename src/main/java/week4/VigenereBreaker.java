package week4;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
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


        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = readDictionary(dr);


        System.out.println("Decrypted message: " + breakForAllLangs(message, languages));
    }

    public HashMap<String, HashSet<String>> readDictionary(DirectoryResource dr) {
        HashMap<String, HashSet<String>> languages = new HashMap<>();
        HashSet<String> dictionary = new HashSet<>();

        for (File file : dr.selectedFiles()) {
            String language = file.getName();

            FileResource fr = new FileResource(file);

            for (String line : fr.lines()) {
                String word = line.toLowerCase();

                dictionary.add(word);
            }
            languages.put(language, dictionary);
        }
        return languages;
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
            int[] keys = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
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

        int[] rightKeys = tryKeyLength(encrypted, keyLength, mostCommonCharIn(dictionary));
        VigenereCipher cipher = new VigenereCipher(rightKeys);

        System.out.println(Arrays.toString(rightKeys));
        System.out.println(keyLength);

        return cipher.decrypt(encrypted);
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> counts = new HashMap<>();

        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if (!counts.containsKey(c)) {
                    counts.put(c, 1);
                } else {
                    counts.put(c, counts.get(c) + 1);
                }
            }
        }
        return Collections.max(counts.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        ArrayList<Integer> max = new ArrayList<>();
        HashSet<String> chosenDict = null;
        int value = 0;

        for (String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            String decryptedWithDifLangs = breakForLanguage(encrypted, dictionary);

            max.add(countWords(decryptedWithDifLangs, dictionary));
            Integer maxMatch = max.stream().max(Comparator.comparing(Integer::intValue)).orElseThrow(NoSuchElementException::new);

            if (maxMatch > value) {
                value = maxMatch;
                chosenDict = dictionary;
            }
        }

        return breakForLanguage(encrypted, chosenDict);
    }
}
