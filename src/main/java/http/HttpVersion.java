package http;

public enum HttpVersion {
    HTTP_1_1("HTTP/1.1"),
    ;

    private final String value;

    HttpVersion(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
