package week1;
/**
 * Simulate rolling two six-sided die, keep statistics
 *
 * @author Duke Software Team
 * @version 1.0
 */

import java.util.Random;

public class DiceRolling {
    public static void simulate(int rolls) {
        Random rand = new Random();
        int[] counter = new int[13];

        for (int k = 0; k < rolls; k++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            System.out.println(d1 + " + " + d2 + " = " + (d1 + d2));

            counter[d1 + d2] += 1;
        }

        for (int i = 2; i < counter.length; i++) {
            System.out.println(i + "'s=\t" + counter[i] + "\t" + 100.0 * counter[i] / rolls);
        }
    }


    public void simpleSimulate(int rolls) {
        Random rand = new Random();
        int twos = 0;
        int twelves = 0;

        for (int k = 0; k < rolls; k++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            if (d1 + d2 == 2) {
                twos += 1;
            } else if (d1 + d2 == 12) {
                twelves += 1;
            }
        }

        System.out.println("2's=\t" + twos + "\t" + 100.0 * twos / rolls);
        System.out.println("12's=\t" + twelves + "\t" + 100.0 * twelves / rolls);
    }

    public static void main(String[] args) {
        simulate(10);
//        simulate(10000);
    }
}
