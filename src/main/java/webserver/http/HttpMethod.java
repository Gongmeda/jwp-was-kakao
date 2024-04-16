package webserver.http;

public enum HttpMethod {
    GET,
    POST,
    PUT,
    PATCH,
    DELETE;

    public static HttpMethod resolve(String name) {
        return valueOf(name.toUpperCase());
    }
}
