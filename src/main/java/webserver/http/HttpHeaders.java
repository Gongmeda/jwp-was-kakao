package webserver.http;

import static java.util.Objects.requireNonNull;

import java.util.*;
import java.util.stream.Collectors;

public class HttpHeaders {

    private static final String CONTENT_TYPE = "Content-Type".toLowerCase();
    private static final String CONTENT_LENGTH = "Content-Length".toLowerCase();
    private static final String COOKIE = "Cookie".toLowerCase();
    private static final String SET_COOKIE = "Set-Cookie".toLowerCase();

    private static final String KEY_VALUE_SPLITTER = ": ";
    public static final String VALUE_DELIMITER = ",";

    private final Map<String, List<String>> headers;
    private final CookieStore cookieStore;

    public static HttpHeaders empty() {
        return new HttpHeaders(new TreeMap<>(), CookieStore.empty());
    }

    public static HttpHeaders parse(String text) {
        if (Objects.isNull(text) || text.isEmpty()) {
            return empty();
        }
        Map<String, List<String>> headers = Arrays.stream(text.split(System.lineSeparator()))
            .map(line -> line.split(KEY_VALUE_SPLITTER))
            .collect(Collectors.toMap(
                pair -> pair[0].toLowerCase(),
                pair -> Arrays.asList(pair[1].split(VALUE_DELIMITER))));
        if (headers.containsKey(COOKIE)) {
            String cookieValue = headers.remove(COOKIE).get(0);
            return new HttpHeaders(headers, CookieStore.parse(cookieValue));
        }
        return new HttpHeaders(headers, CookieStore.empty());
    }

    private HttpHeaders(Map<String, List<String>> headers, CookieStore cookieStore) {
        requireNonNull(headers);
        this.headers = new TreeMap<>(headers);
        this.cookieStore = cookieStore;
    }

    public List<String> get(String key) {
        return headers.get(key.toLowerCase());
    }

    public void add(String key, String value) {
        requireNonNull(key);
        requireNonNull(value);

        String lowerCaseKey = key.toLowerCase();
        if (!headers.containsKey(lowerCaseKey)) {
            headers.put(lowerCaseKey, new ArrayList<>());
        }
        headers.get(lowerCaseKey).add(value);
    }

    public CookieStore getCookieStore() {
        return cookieStore;
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
        Map<String, List<String>> headersCopy = new TreeMap<>(headers);

        for (HttpCookie cookie : cookieStore.getCookies()) {
            headersCopy.put(SET_COOKIE, List.of(cookie.toString()));
        }

        return headersCopy.entrySet().stream()
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
