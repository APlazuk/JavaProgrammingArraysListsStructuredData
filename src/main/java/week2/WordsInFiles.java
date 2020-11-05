package week2;

import edu.duke.DirectoryResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> fileNames;

    public WordsInFiles() {
        fileNames = new HashMap<>();
    }

//    Write a private void method named addWordsFromFile that has one parameter f of type File.
//    This method should add all the words from f into the map.
//    If a word is not in the map, then you must create a new ArrayList of type String with this word,
//    and have the word map to this ArrayList. If a word is already in the map, then add the current filename to its ArrayList,
//    unless the filename is already in the ArrayList. You can use the File method getName to get the filename of a file.

    private void addWordsFromFile(File f) {

        ArrayList<String> files;

        try {
            Scanner scanner = new Scanner(f);

            while (scanner.hasNext()) {
                String s = scanner.next();
                String word = s.toLowerCase();

                if (!fileNames.containsKey(word)) {
                    files = new ArrayList<>();
                } else {
                    files = fileNames.get(word);
                }

                if (!files.contains(f.getName())) {
                    files.add(f.getName());
                    fileNames.put(word, files);
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }

    }

//    Write a void method named buildWordFileMap that has no parameters.
//    This method first clears the map, and then uses a DirectoryResource to select a group of files.
//    For each file, it puts all of its words into the map by calling the method addWordsFromFile.
//    The remaining methods to write all assume that the HashMap has been built.

    public void buildWordFileMap() {
        fileNames.clear();

        DirectoryResource dr = new DirectoryResource();

        for (File file : dr.selectedFiles()) {
            addWordsFromFile(file);
        }
    }

//    Write the method maxNumber that has no parameters.
//    This method returns the maximum number of files any word appears in, considering all words from a group of files.
//    In the example above, there are four files considered.
//    No word appears in all four files. Two words appear in three of the files, so maxNumber on those four files would return 3.
//    This method assumes that the HashMap has already been constructed.

    public int maxNumber() {

        List<Integer> collect = fileNames
                .entrySet()
                .stream()
                .map(m -> m.getValue().size())
                .collect(Collectors.toList());

        int result = collect.stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);

        return result;
    }

//    Write the method wordsInNumFiles that has one integer parameter called number.
//    This method returns an ArrayList of words that appear in exactly number files.
//    In the example above, the call wordsInNumFiles(3) would return an ArrayList with the words “cats” and “and”,
//    and the call wordsInNumFiles(2) would return an ArrayList with the words “love”, “are”, and “dogs”,
//    all the words that appear in exactly two files.

    public ArrayList<String>  wordsInNumFiles(int number) {

        List<String> collect = fileNames.entrySet()
                .stream()
                .filter(e -> e.getValue().size() == number)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());


        return (ArrayList<String>) collect;
    }


//    Write the void method printFilesIn that has one String parameter named word.
//    This method prints the names of the files this word appears in, one filename per line.
//    For example, in the example above, the call printFilesIn(“cats”)
//    would print the three filenames: brief1.txt, brief3.txt, and brief4.txt, each on a separate line.

    public void printFilesIn(String word){
        List<ArrayList<String>> collect = fileNames.entrySet()
                .stream()
                .filter(e -> e.getKey().equals(word))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        collect.stream().forEach(System.out::println);
    }

    public void tester() {

        buildWordFileMap();
        System.out.println(maxNumber());
        System.out.println(wordsInNumFiles(2));
        printFilesIn("cats");
    }

}



