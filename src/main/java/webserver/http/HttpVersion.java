package webserver.http;

public enum HttpVersion {
    HTTP_1_1("HTTP/1.1"),
    ;

    private final String value;

    HttpVersion(String value) {
        this.value = value;
    }

    public static HttpVersion resolve(String value) {
        for (HttpVersion v : values()) {
            if (v.value.equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
