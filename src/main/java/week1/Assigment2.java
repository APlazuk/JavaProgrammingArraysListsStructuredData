package week1;

import edu.duke.FileResource;

public class Assigment2 {
    public static void main(String[] args) {
        FileResource fr = new FileResource();
        String messsage = fr.asString();

        CaesarCipherTwoKeys cc = new CaesarCipherTwoKeys(2, 2);
        String encrypt2 = cc.encrypt2(messsage);
        System.out.println("Encrypted2: " + encrypt2);
        System.out.println("Decrypted2: " + cc.decrypt2(encrypt2));

        System.out.println(cc.decrypt(messsage));

        TestCaesarCipherTwoKeys ccTwoKeys = new TestCaesarCipherTwoKeys();
        CaesarCipherTwoKeys caesarCipherTwoKeys = new CaesarCipherTwoKeys(14, 24);
        String encrypt = caesarCipherTwoKeys.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
        System.out.println(encrypt);

        System.out.println(caesarCipherTwoKeys.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy."));
        System.out.println(ccTwoKeys.breakCaesarCipher("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
//        ccTwoKeys.simpleTests();
    }
}
