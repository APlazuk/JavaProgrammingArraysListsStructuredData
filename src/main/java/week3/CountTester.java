package week3;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class CountTester {

    public void testCounts() throws ParseException {
        LogAnalyzer la = new LogAnalyzer();

        String filename = "weblog3-short_log";
        la.readFile(filename);
        HashMap<String, Integer> countVisitsPerIP = la.countVisitsPerIP();
        System.out.println(countVisitsPerIP);

        System.out.println("Maximum number of visits: " + la.mostNumberVisitsByIP(countVisitsPerIP));

        System.out.println("Most common website list: " + la.iPsMostVisits(countVisitsPerIP));

        HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays();
        System.out.println(iPsForDays);

        System.out.println("Most visits at: " + la.dayWithMostIPVisits(iPsForDays));

        System.out.println(la.iPsWithMostVisitsOnDay(iPsForDays, "Sep 30"));
    }
}
