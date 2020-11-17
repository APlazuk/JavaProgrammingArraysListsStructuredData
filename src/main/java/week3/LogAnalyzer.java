package week3;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Date;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);

        for (String line : fr.lines()) {

            LogEntry entry = WebLogParser.parseEntry(line);
            records.add(entry);
        }
    }

    public void printAll() {
        for (LogEntry entry : records) {
            System.out.println(entry);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<>();

        for (LogEntry record : records) {
            String ipAddress = record.getIpAddress();

            if (!uniqueIPs.contains(ipAddress)) {
                uniqueIPs.add(ipAddress);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {

        for (LogEntry record : records) {
            int sc = record.getStatusCode();
            if (sc > num) {
                System.out.println("Status greater than " + num + " : " + sc);
            }
        }
    }

    public int uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIps = new ArrayList<>();

        for (LogEntry record : records) {
            Date accessTime = record.getAccessTime();
            String date = accessTime.toString().toLowerCase();
            String ipAddress = record.getIpAddress();

            if (date.contains(someday.toLowerCase()) && !uniqueIps.contains(ipAddress)) {
                uniqueIps.add(ipAddress);
            }
        }
        return uniqueIps.size();
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIps = new ArrayList<>();

        for (LogEntry record : records) {
            int sc = record.getStatusCode();
            String ipAddress = record.getIpAddress();

            if (sc >= low && sc <= high && !uniqueIps.contains(ipAddress)) {
                uniqueIps.add(ipAddress);
            }
        }
        return uniqueIps.size();
    }
}
