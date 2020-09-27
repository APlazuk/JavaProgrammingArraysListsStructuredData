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

    public static void emphasize(String phrase, char ch){

    }

    public static void testIsVowel() {
        char ch = 'A';
        System.out.println(isVowel(ch));
    }

    public static void testReplaceVowels(){
        String phrase = "cAr iS GreEn";
        char ch = '*';
        System.out.println(replaceVowels(phrase, ch));
    }

    public static void main(String[] args) {
//        testIsVowel();
        testReplaceVowels();
    }
}
