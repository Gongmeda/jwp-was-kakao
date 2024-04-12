package http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class HttpHeaders {

    private final Map<String, List<String>> headers;

    private HttpHeaders(Map<String,List<String>> headers) {
        this.headers = new HashMap<>(headers);
    }

    public static HttpHeaders of(Map<String,List<String>> headerMap) {
        requireNonNull(headerMap);
        return new HttpHeaders(headerMap);
    }

    public Map<String,List<String>> map() {
        return headers;
    }
}
