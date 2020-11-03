package week2;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class CharactersInPlay {

    private ArrayList<String> characters;
    private ArrayList<Integer> chFreqs;

    public CharactersInPlay() {
        characters = new ArrayList<>();
        chFreqs = new ArrayList<>();
    }

    public void update(String person) {
        int index = characters.indexOf(person);

        if (index == -1) {
            characters.add(person);
            chFreqs.add(1);
        } else {
            int value = chFreqs.get(index);
            chFreqs.set(index, value + 1);
        }
    }

    public void findAllCharacters() {
        characters.clear();
        chFreqs.clear();

        FileResource fr = new FileResource();

        for (String line : fr.lines()) {
            if (line.contains(".")) {
                int index = line.indexOf(".");
                String person = line.substring(0, index);

                if (person.toUpperCase().equals(person)) {
                    update(person);
                }
            }
        }
    }

    public void tester() {
        findAllCharacters();

        for (int i = 0; i < characters.size(); i++) {
            System.out.println(characters.get(i) + "\t" + chFreqs.get(i));
        }

        int indexOfMax = findIndexOfMax();
        System.out.println("max word/freq: " + characters.get(indexOfMax) + " " + chFreqs.get(indexOfMax));

        int num1 = 3;
        int num2 = 6;

        charactersWithNumParts(num1, num2);
    }

    public int findIndexOfMax() {
        int maxValue = chFreqs.get(0);
        int maxIndex = 0;

        for (int i = 0; i < chFreqs.size(); i++) {
            if (chFreqs.get(i) > maxValue) {
                maxValue = chFreqs.get(i);
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public void charactersWithNumParts(int num1, int num2) {

        for (int i = 0; i < chFreqs.size(); i++) {
            if (num1 <= num2) {
                if (chFreqs.get(i) >= num1 && chFreqs.get(i) <= num2) {
                    System.out.println(characters.get(i));
                }
            }
        }
    }
}
