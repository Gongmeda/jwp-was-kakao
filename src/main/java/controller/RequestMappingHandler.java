package controller;

import java.util.Map;
import org.springframework.util.StringUtils;
import webserver.http.URI;

public class RequestMappingHandler {

    private static final Map<String, Controller> REQUEST_MAP;
    private static final Controller DEFAULT_CONTROLLER = new NotFoundController();
    private static final Controller DEFAULT_RESOURCE_CONTROLLER = new ResourceController();

    static {
        REQUEST_MAP = Map.of(
            "/user/create", new UserController()
        );
    }

    public static Controller getController(URI uri) {
        if (isFile(uri)) {
            return DEFAULT_RESOURCE_CONTROLLER;
        }

        return REQUEST_MAP.getOrDefault(uri.getPath(), DEFAULT_CONTROLLER);
    }

    private static boolean isFile(URI uri) {
        String filenameExtension = StringUtils.getFilenameExtension(uri.getPath());
        return filenameExtension != null;
    }
}
