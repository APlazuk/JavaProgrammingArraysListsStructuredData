package week3;

import edu.duke.FileResource;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            String date = parseDate(accessTime);
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

    public HashMap<String, Integer> countVisitsPerIP() {

        HashMap<String, Integer> counts = new HashMap<>();

        for (LogEntry record : records) {
            String ipAddress = record.getIpAddress();

            if (!counts.containsKey(ipAddress)) {
                counts.put(ipAddress, 1);
            } else {
                counts.put(ipAddress, counts.get(ipAddress) + 1);
            }
        }
        return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {

        Integer max = counts.values().stream().max(Comparator.comparing(Integer::intValue)).orElseThrow(NoSuchElementException::new);

        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> mostCommonWebsites = new ArrayList<>();
        int max = 0;
//
//        String s = counts.keySet().stream().max(Comparator.comparing(counts::get)).orElseThrow(NoSuchElementException::new);
//        mostCommonWebsites.add(s);

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            Integer value = entry.getValue();

            if (value >= max) {
                max = value;
                mostCommonWebsites.add(entry.getKey());
            }
        }

        return mostCommonWebsites;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> result = new HashMap<>();

        for (LogEntry record : records) {
            Date accessTime = record.getAccessTime();
            String day = parseDate(accessTime);
            String ipAddress = record.getIpAddress();

            if (!result.containsKey(day)) {
                ArrayList<String> iPsForSelectedDay = new ArrayList<>();
                iPsForSelectedDay.add(ipAddress);
                result.put(day, iPsForSelectedDay);
            } else {
                if (!result.containsValue(ipAddress)) {
                    result.get(day).add(ipAddress);
                }
            }
        }
        return result;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> recordsAtDay) {

        return Collections.max(recordsAtDay.entrySet(), Comparator.comparing(m -> m.getValue().size())).getKey();
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> input, String day) {
        return null;
    }

    private String parseDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd", Locale.US);
        return sdf.format(date);
    }
}
