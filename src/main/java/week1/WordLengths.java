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

            if (Character.isLetter(c) || c == '\'' ) {
                wordBuilder.append(c);
            }
        }
        return wordBuilder.toString();
    }

    public static void testCountWordsLengths(){
        FileResource resource = new FileResource("src/main/resources/data/smallHamlet.txt");
        int[] count = new int[31];
        countWordLengths(resource, count);
    }

    public static void main(String[] args) {
        testCountWordsLengths();
    }
}
