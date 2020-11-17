package week3;
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {

        String filename = "weblog1_log";
        LogAnalyzer la = new LogAnalyzer();

        la.readFile(filename);
        la.printAll();
        System.out.println("Unique IPs: " + la.countUniqueIPs());
        la.printAllHigherThanNum(400);
        System.out.println("Unique IPs refers to selected day: " + la.uniqueIPVisitsOnDay("Mar 17"));
        System.out.println("Unique IPs refers to selected day: " + la.uniqueIPVisitsOnDay("Sep 30"));

        System.out.println("Unique IPs refers in range: " + la.countUniqueIPsInRange(200, 299));
        System.out.println("Unique IPs refers in range: " + la.countUniqueIPsInRange(300, 399));
    }
}
