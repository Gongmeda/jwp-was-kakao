package http;

import org.springframework.http.HttpStatus;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HttpResponse {

    private final HttpHeaders headers;
    private final HttpStatus status;
    private final HttpVersion version;
    private final byte[] body;

    public HttpResponse(HttpHeaders headers, HttpStatus status, HttpVersion version, byte[] body) {
        this.status = status;
        this.headers = headers;
        this.version = version;
        this.body = body;
    }

    public void respond(DataOutputStream dos) throws IOException {
        dos.writeBytes(String.format("%s %d %s \r\n", version.toString(), status.value(), status.getReasonPhrase()));
        writeHeaders(dos);
        dos.writeBytes("\r\n");

        if (body != null) {
            dos.write(body, 0, body.length);
        }
        dos.flush();
    }

    private void writeHeaders(DataOutputStream dos) throws IOException {
        if (body != null && body.length > 0) {
            dos.writeBytes("Content-Length: " + body.length + "\r\n");
        }
        for (Map.Entry<String, List<String>> entry : headers.map().entrySet()) {
            dos.writeBytes(entry.getKey() + ": " + String.join(",", entry.getValue()) + "\r\n");
        }
    }
}
