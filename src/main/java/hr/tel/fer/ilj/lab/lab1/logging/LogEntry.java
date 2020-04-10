package hr.tel.fer.ilj.lab.lab1.logging;

/**
 * This class represents single log entry.
 *
 * @author Darko Juraga as part of <a href="https://www.fer.unizg.hr/predmet/ilj_a">Information, Logic and Languages</a> course.
 */
public class LogEntry {
    private String ip;
    private String dateTime;
    private String httpMethod;
    private String path;
    private String httpVersion;
    private int status;
    private String clientID;

    /**
     * Constructor.
     */
    public LogEntry() {
    }

    /**
     * IP getter.
     *
     * @return IP.
     */
    public String getIp() {
        return ip;
    }

    /**
     * IP setter.
     *
     * @param ip IP.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * DateTime getter.
     *
     * @return DateTime.
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * DateTime setter.
     *
     * @param dateTime DateTime.
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * HttpMethod getter.
     *
     * @return HttpMethod.
     */
    public String getHttpMethod() {
        return httpMethod;
    }

    /**
     * HttpMethod setter.
     *
     * @param httpMethod HttpMethod.
     */
    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * Path setter.
     *
     * @param path Path.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * HttpVersion getter.
     *
     * @return HttpVersion.
     */
    public String getHttpVersion() {
        return httpVersion;
    }

    /**
     * HttpVersion Setter.
     *
     * @param httpVersion HttpVersion.
     */
    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    /**
     * Status getter.
     *
     * @return Status.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Status setter.
     *
     * @param status Status.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * CliendID setter.
     *
     * @param clientID ClientID.
     */
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    @Override
    public String toString() {
        return ip + " " + dateTime + " " + httpMethod + " " + path + " " + httpVersion + " " + status + " \"" + clientID + "\"";
    }
}