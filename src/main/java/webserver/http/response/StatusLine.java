package webserver.http.response;

import webserver.http.HttpStatus;
import webserver.http.HttpVersion;

public class StatusLine {

    private HttpVersion version;
    private HttpStatus status;

    public StatusLine(HttpVersion version, HttpStatus status) {
        this.version = version;
        this.status = status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("%s %d %s", version.toString(), status.value(), status.getReasonPhrase());
    }
}
