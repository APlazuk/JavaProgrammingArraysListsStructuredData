package week2;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.*;

public class GladLibMap {

    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> seenWordsList;
    private ArrayList<String> seenCategories;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap() {
        this.seenCategories = new ArrayList<>();
        this.myMap = new HashMap<>();

        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        seenWordsList = new ArrayList<>();

    }

    public GladLibMap(String source) {
        initializeFromSource(source);
        myRandom = new Random();
    }

//    Modify the method initializeFromSource to create an Array of categories and then iterate over this Array.
//    For each category, read in the words from the associated file,
//    create an ArrayList of the words (using the method readIt), and put the category and ArrayList into the HashMap.

    private void initializeFromSource(String source) {

        ArrayList<String> categories = new ArrayList<>();
        categories.add("adjective");
        categories.add("noun");
        categories.add("color");
        categories.add("country");
        categories.add("name");
        categories.add("animal");
        categories.add("timeframe");
        categories.add("verb");
        categories.add("fruit");


        for (String category : categories) {
            String path = source + "/" + category + ".txt";
            ArrayList<String> words = readIt(path);

            myMap.put(category, words);
        }

        for (Map.Entry<String, ArrayList<String>> entry : myMap.entrySet()) {
            String category = entry.getKey();

            int index = seenCategories.indexOf(category);

            if (index == -1) {
                seenCategories.add(category);
            }
        }
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

//    Modify the method getSubstitute
//    to replace all the if statements that use category labels with one call
//    to randomFrom that passes the appropriate ArrayList from myMap.

    private String getSubstitute(String label) {

        for (Map.Entry<String, ArrayList<String>> entry : myMap.entrySet()) {
            String category = entry.getKey();
            ArrayList<String> wordList = entry.getValue();

            if (label.equals(category)) {
                return randomFrom(wordList);
            }
        }

        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));

        int index = seenWordsList.indexOf(sub);

        if (index == -1) {
            seenWordsList.add(sub);
            return prefix + sub + suffix;
        }
        return processWord(w);

    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory() {
        seenWordsList.clear();

        System.out.println("\n");
        String story = fromTemplate("src/main/resources/data/madtemplate.txt");
        printOut(story, 60);

        System.out.println("\n");
        System.out.println("number of words that were replaced " + (long) seenWordsList.size());

    }

//    Write a new method named totalWordsInMap with no parameters.
//    This method returns the total number of words in all the ArrayLists in the HashMap.
//    After printing the GladLib, call this method and print out the total number of words that were possible to pick from.

    public int totalWordsInMap() {

        return myMap.values()
                .stream()
                .mapToInt(ArrayList::size)
                .sum();
    }

//    Write a new method named totalWordsConsidered with no parameters.
//    This method returns the total number of words in the ArrayLists of the categories that were used for a particular GladLib.
//    If only noun, color, and adjective were the categories used in a GladLib, then only calculate the sum of all the words in those three ArrayLists.
//    Hint: You will need to keep track of the categories used in solving the GladLib, then compute this total.

    public int totalWordsConsidered() {

        int result = 0;

        for (String next : seenCategories) {

            for (Map.Entry<String, ArrayList<String>> entry : myMap.entrySet()) {
                ArrayList<String> wordList = entry.getValue();
                String category = entry.getKey();

                if (category.equals(next)) {

                    int size = wordList.size();
                    result += size;
                }
            }
        }
        return result;
    }
}
