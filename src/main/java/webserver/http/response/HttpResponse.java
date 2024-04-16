package webserver.http.response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;
import webserver.http.ContentType;
import webserver.http.HttpHeaders;
import webserver.http.HttpStatus;
import webserver.http.HttpVersion;

public class HttpResponse {

    private static final HttpVersion DEFAULT_PROTOCOL_VERSION = HttpVersion.HTTP_1_1;
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.OK;

    private StatusLine statusLine;
    private HttpHeaders headers;
    private byte[] body;

    public HttpResponse() {
        this.statusLine = new StatusLine(DEFAULT_PROTOCOL_VERSION, DEFAULT_STATUS);
        this.headers = HttpHeaders.empty();
    }

    public void respond(DataOutputStream dos) throws IOException {
        dos.writeBytes(statusLine.toString() + System.lineSeparator());
        dos.writeBytes(headers.toString() + System.lineSeparator());
        dos.writeBytes(System.lineSeparator());

        if (Objects.nonNull(body) && body.length != 0) {
            dos.write(body, 0, body.length);
        }
        dos.flush();
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setBody(byte[] body, ContentType contentType) {
        this.body = body;
        headers.setContentType(contentType.getContentType());
        headers.setContentLength(body.length);
    }

    public void statusResponse(HttpStatus status) {
        statusLine.setStatus(status);
        setBody(String.format("<h1>%s</h1>", status.getReasonPhrase()).getBytes(), ContentType.HTML);
    }
}
