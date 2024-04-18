package webserver.http;

public class HttpCookie {

    private static final String ROOT_PATH = "/";

    private final String name;
    private final String value;
    private final String path;

    public HttpCookie(String name, String value, String path) {
        this.name = name;
        this.value = value;
        this.path = path;
    }

    public HttpCookie(String name, String value) {
        this(name, value, ROOT_PATH);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getPath() {
        return path;
    }

    public String toResponseString() {
        return String.format("%s=%s; path=%s", name, value, path);
    }
}
