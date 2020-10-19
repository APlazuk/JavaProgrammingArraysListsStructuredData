package week1;

import edu.duke.FileResource;

public class WordLengths {

    public static void countWordLengths(FileResource resource, int[] counts) {

        for (String word : resource.words()) {
            word = word.toLowerCase();
            String newWord = noPunctuation(word);
            int length = newWord.length();

            counts[length] += 1;

            if (length >= 30 || length >= (counts.length - 1)) {
                counts[length] += 1;
            }

            System.out.println(counts[length] + " = " + newWord + " length = " + newWord.length());
        }

    }

    public static String noPunctuation(String word) {

        StringBuilder wordBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = Character.toLowerCase(word.charAt(i));

            if (Character.isLetter(c) || c == '\'') {
                wordBuilder.append(c);
            }
        }
        return wordBuilder.toString();
    }

    public static int indexOfMax(int[] values) {
        int maxValue = 0;

        for (int i = 0; i < values.length - 1; i++) {
            int value = values[i];
            if (value > maxValue) {
                maxValue = i;
            }
        }
        return maxValue;
    }

    public static void testCountWordsLengths() {
        FileResource resource = new FileResource("src/main/resources/data/smallHamlet.txt");
        int[] count = new int[31];
        countWordLengths(resource, count);
        System.out.println(indexOfMax(count));
    }

    public static void main(String[] args) {
        testCountWordsLengths();
    }
}
