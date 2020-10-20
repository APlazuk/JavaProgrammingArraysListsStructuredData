package week1;

import edu.duke.FileResource;

public class Assigment2 {
    public static void main(String[] args) {
//        FileResource fr = new FileResource();
//        String messsage = fr.asString();
//
//        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(2, 2);
//        String encrypt2 = cc.encrypt2(messsage);
//        System.out.println("Encrypted2: " + encrypt2);
//        System.out.println("Decrypted2: " + cc.decrypt2(encrypt2));

        TestCaesarCipherTwoKeys ccTwoKeys = new TestCaesarCipherTwoKeys();
        ccTwoKeys.simpleTests();
    }
}
