package week2;
/**
 * Find out how many times each word occurs, and
 * in particular the most frequently occurring word.
 *
 * @author Duke Software Team
 * @version 1.0
 */

import edu.duke.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();

        FileResource resource = new FileResource();

        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }

    public void tester() {
        findUnique();

        System.out.println("# unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }

        int index = findIndexOfMax();
        System.out.println("max word/freq: " + myWords.get(index) + " " + myFreqs.get(index));

    }

    public int findIndexOfMax() {

        return myFreqs.stream().max(Comparator.comparing(myFreqs::get)).orElseThrow(NoSuchElementException::new);
    }
}
