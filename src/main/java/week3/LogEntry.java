package week3;

import java.util.Date;

public class LogEntry {

    private String ipAddress;
    private Date accessTime;
    private String request;
    private  int statusCode;
    private  int byteReturned;


    public LogEntry(String ipAddress, Date accessTime, String request, int statusCode, int byteReturned) {
        this.ipAddress = ipAddress;
        this.accessTime = accessTime;
        this.request = request;
        this.statusCode = statusCode;
        this.byteReturned = byteReturned;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public String getRequest() {
        return request;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getByteReturned() {
        return byteReturned;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "ipAddress='" + ipAddress + '\'' +
                ", accessTime=" + accessTime +
                ", request='" + request + '\'' +
                ", statusCode=" + statusCode +
                ", byteReturned=" + byteReturned +
                '}';
    }
}
