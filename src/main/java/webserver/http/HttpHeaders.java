package webserver.http;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class HttpHeaders {

    private static final HttpHeaders DEFAULT_HEADERS = new HttpHeaders(Collections.unmodifiableMap(new HashMap<>()));

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH = "Content-Length";

    private static final String KEY_VALUE_SPLITTER = ": ";
    public static final String VALUE_DELIMITER = ",";

    private final Map<String, List<String>> headers;

    public static HttpHeaders parse(String text) {
        if (Objects.isNull(text) || text.isEmpty()) {
            return DEFAULT_HEADERS;
        }
        Map<String, List<String>> headers = Arrays.stream(text.split(System.lineSeparator()))
            .map(line -> line.split(KEY_VALUE_SPLITTER))
            .collect(Collectors.toMap(
                pair -> pair[0],
                pair -> Arrays.asList(pair[1].split(VALUE_DELIMITER))));
        return new HttpHeaders(headers);
    }

    public HttpHeaders() {
        this.headers = new HashMap<>();
    }

    public HttpHeaders(Map<String,List<String>> headers) {
        requireNonNull(headers);
        this.headers = new HashMap<>(headers);
    }

    public int getContentLength() {
        List<String> contentLength = headers.get(CONTENT_LENGTH);
        if (Objects.isNull(contentLength) || contentLength.size() != 1 || Objects.isNull(contentLength.get(0))) {
            return 0;
        }
        return Integer.parseInt(contentLength.get(0));
    }

    public void setContentType(String contentType) {
        headers.put(CONTENT_TYPE, List.of(contentType));
    }

    public void setContentLength(int contentLength) {
        headers.put(CONTENT_LENGTH, List.of(String.valueOf(contentLength)));
    }

    @Override
    public String toString() {
        return headers.entrySet().stream()
                   .map(entry -> entry.getKey() + KEY_VALUE_SPLITTER + String.join(VALUE_DELIMITER, entry.getValue()))
                   .collect(Collectors.joining(System.lineSeparator()));
    }
}
