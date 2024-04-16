package webserver.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import utils.IOUtils;
import webserver.http.HttpHeaders;

public class HttpRequest {

    private final RequestLine requestLine;
    private final HttpHeaders headers;
    private final Object body;

    public static HttpRequest from(BufferedReader bf) throws IOException {
        RequestLine requestLine = RequestLine.parse(bf.readLine());
        HttpHeaders httpHeaders = HttpHeaders.parse(extractHeaderString(bf));
        String body = IOUtils.readData(bf, httpHeaders.getContentLength());
        return new HttpRequest(requestLine, httpHeaders, body);
    }

    private static String extractHeaderString(BufferedReader bf) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while (Objects.nonNull(line = bf.readLine()) && !line.isEmpty()) {
            stringBuilder.append(line).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private HttpRequest(RequestLine requestLine, HttpHeaders headers, Object body) {
        this.requestLine = requestLine;
        this.headers = headers;
        this.body = body;
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public Object getBody() {
        return body;
    }
}
