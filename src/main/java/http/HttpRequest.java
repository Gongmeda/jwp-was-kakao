package http;

public class HttpRequest {
    private final RequestLine requestLine;
    private final HttpHeaders headers;
    private final HttpQueryParams queryParams;
    private final Object body;

    public HttpRequest(RequestLine requestLine, HttpHeaders headers, HttpQueryParams queryParams, Object body) {
        this.requestLine = requestLine;
        this.headers = headers;
        this.queryParams = queryParams;
        this.body = body;
    }

    public HttpQueryParams getQueryParams() {
        return queryParams;
    }

    public RequestLine getStartLine() {
        return requestLine;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public Object getBody() {
        return body;
    }
}
