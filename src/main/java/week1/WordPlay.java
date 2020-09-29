package week1;

public class WordPlay {

    public static boolean isVowel(char ch) {
        String vowels = "aeiou";
        if (vowels.toUpperCase().indexOf(ch) != -1) {
            return true;
        }
        return false;
    }

    public static String replaceVowels(String phrase, char ch) {

        StringBuilder phraseRepVow = new StringBuilder(phrase.toUpperCase());

        for (int i = 0; i < phraseRepVow.length(); i++) {
            char currChar = phraseRepVow.charAt(i);
            if (isVowel(currChar)) {
                phraseRepVow.setCharAt(i, ch);
            }
        }
        return phraseRepVow.toString();
    }

    public static String emphasize(String phrase, char ch) {
        StringBuilder phraseRep = new StringBuilder(phrase.toLowerCase());
        ch = Character.toLowerCase(ch);

        for (int i = 0; i < phraseRep.length(); i++) {
            char currChar = phraseRep.charAt(i);

            if (ch == currChar && (i % 2 == 0)) {
                phraseRep.setCharAt(i, '*');
            }
            if (ch == currChar && i % 2 ==1){
                phraseRep.setCharAt(i , '+');
            }
        }
        return phraseRep.toString();
    }

    public static void testIsVowel() {
        char ch = 'A';
        System.out.println(isVowel(ch));
    }

    public static void testReplaceVowels() {
        String phrase = "cAr iS GreEn";
        char ch = '*';
        System.out.println(replaceVowels(phrase, ch));
    }

    public static void testEmphasize(){
        String phrase = "Mary Bella Abracadabra";
        char ch = 'a';
        System.out.println(emphasize(phrase, ch));
    }

    public static void main(String[] args) {
//        testIsVowel();
//        testReplaceVowels();
    testEmphasize();
    }
}
