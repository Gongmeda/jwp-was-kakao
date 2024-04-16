package controller;

import java.util.Map;
import webserver.http.URI;

public class RequestMappingHandler {

    private static final Map<URI, Controller> REQUEST_MAP;

    static {
        REQUEST_MAP = Map.of(
            URI.parse("/user/create"), new UserController()
        );
    }

    public static Controller getController(URI uri) {
        return REQUEST_MAP.getOrDefault(uri, new Controller() {});
    }
}
