package webserver.http.request;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import webserver.http.HttpMethod;
import webserver.http.HttpVersion;
import webserver.http.URI;

public class RequestLine {

    private final HttpMethod method;
    private final URI uri;
    private final HttpVersion version;

    public RequestLine(HttpMethod method, URI uri, HttpVersion version) {
        this.method = method;
        this.uri = uri;
        this.version = version;
    }

    public static RequestLine parse(String text) {
        String[] tokens = text.split(" ");
        HttpMethod httpMethod = HttpMethod.resolve(tokens[0]);
        URI uri = URI.parse(URLDecoder.decode(tokens[1], StandardCharsets.UTF_8));
        HttpVersion version = HttpVersion.resolve(tokens[2]);
        return new RequestLine(httpMethod, uri, version);
    }

    public HttpMethod getMethod() {
        return method;
    }

    public URI getUri() {
        return uri;
    }

    public HttpVersion getVersion() {
        return version;
    }
}
