package webserver.http;

import static java.util.Objects.requireNonNull;

import java.util.*;
import java.util.stream.Collectors;

public class HttpHeaders {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH = "Content-Length";

    private static final String KEY_VALUE_SPLITTER = ": ";
    public static final String VALUE_DELIMITER = ",";

    private final Map<String, List<String>> headers;

    public static HttpHeaders empty() {
        return new HttpHeaders(new TreeMap<>());
    }

    public static HttpHeaders parse(String text) {
        if (Objects.isNull(text) || text.isEmpty()) {
            return empty();
        }
        Map<String, List<String>> headers = Arrays.stream(text.split(System.lineSeparator()))
            .map(line -> line.split(KEY_VALUE_SPLITTER))
            .collect(Collectors.toMap(
                pair -> pair[0],
                pair -> Arrays.asList(pair[1].split(VALUE_DELIMITER))));
        return new HttpHeaders(headers);
    }

    private HttpHeaders(Map<String, List<String>> headers) {
        requireNonNull(headers);
        this.headers = new TreeMap<>(headers);
    }

    public List<String> get(String key) {
        return headers.get(key);
    }

    public void add(String key, String value) {
        requireNonNull(key);
        requireNonNull(value);

        if (!headers.containsKey(key)) {
            headers.put(key, new ArrayList<>());
        }
        headers.get(key).add(value);
    }

    public int getContentLength() {
        List<String> contentLength = headers.get(CONTENT_LENGTH);
        if (Objects.isNull(contentLength) || contentLength.size() != 1 || Objects.isNull(contentLength.get(0))) {
            return 0;
        }
        return Integer.parseInt(contentLength.get(0));
    }

    public void setContentType(String contentType) {
        headers.remove(CONTENT_TYPE);
        add(CONTENT_TYPE, contentType);
    }

    public void setContentLength(int contentLength) {
        headers.remove(CONTENT_LENGTH);
        add(CONTENT_LENGTH, String.valueOf(contentLength));
    }

    @Override
    public String toString() {
        return headers.entrySet().stream()
            .map(entry -> entry.getKey() + KEY_VALUE_SPLITTER + String.join(VALUE_DELIMITER, entry.getValue()))
            .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpHeaders that = (HttpHeaders) o;
        return Objects.equals(headers, that.headers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(headers);
    }
}
