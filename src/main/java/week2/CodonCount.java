package week2;

import edu.duke.FileResource;

import java.util.*;


public class CodonCount {

    private HashMap<String, Integer> codonsDNA;


    public CodonCount() {
        codonsDNA = new HashMap<>();
    }
//    Write a void method named buildCodonMap that has two parameters, an int named start and a String named dna. T
//    his method will build a new map of codons mapped to their counts from the string dna with the reading
//    frame with the position start (a value of 0, 1, or 2).
//    You will call this method several times, so make sure your map is empty before building it.

    public void buildCodonMap(int start, String dna) {
        codonsDNA.clear();

        String codon = "";
        int endIndex = dna.length()-(start+3);

        for (int i = start; i < endIndex; i += 3) {
            codon = dna.substring(i, i + 3);

            if (codonsDNA.containsKey(codon)) {
                codonsDNA.put(codon, codonsDNA.get(codon) + 1);
            } else {
                codonsDNA.put(codon, 1);
            }
        }
        for (String s : codonsDNA.keySet()) {
            int occurrences = codonsDNA.get(s);
            System.out.println(occurrences + "\t" + s);
        }
    }


    public String getMostCommonCodon() throws Exception {
        Optional<String> s = codonsDNA.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getKey);

        if (!s.isPresent()) {
            throw new Exception("No such element");
        }

        int size = codonsDNA.keySet().size();
        System.out.println("total unique codons: " + size);
        return String.valueOf(s);
    }


    public void printCodonCounts(int start, int end) {

        for (Map.Entry<String, Integer> entry : codonsDNA.entrySet()) {
            Integer value = entry.getValue();
            if (start <= end) {
                if (value >= start && value <= end) {
                    System.out.println(entry.getKey());
                }
            }
        }
    }


    public void tester() throws Exception {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        int start = 1;

        buildCodonMap(start, dna);
        System.out.println(getMostCommonCodon());

        int num1 = 5;
        int num2 = 10;

        printCodonCounts(num1, num2);

    }
}
