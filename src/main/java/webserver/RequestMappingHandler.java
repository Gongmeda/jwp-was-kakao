package webserver;

import controller.*;
import controller.advice.ControllerAdvice;
import controller.advice.SessionControllerAdvice;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.util.StringUtils;
import webserver.http.URI;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public class RequestMappingHandler {

    private static final Map<String, Controller> REQUEST_MAP;
    private static final List<ControllerAdvice> ADVICES;
    private static final Controller DEFAULT_CONTROLLER = new NotFoundController();
    private static final Controller DEFAULT_RESOURCE_CONTROLLER = new ResourceController();

    static {
        REQUEST_MAP = Map.of(
            "/user/create", new UserCreateController(),
            "/user/login", new UserLoginController(),
            "/user/list", new UserListController()
        );
        ADVICES = List.of(
            new SessionControllerAdvice()
        );
    }

    private static Controller getController(URI uri) {
        if (isFile(uri)) {
            return DEFAULT_RESOURCE_CONTROLLER;
        }

        return REQUEST_MAP.getOrDefault(uri.getPath(), DEFAULT_CONTROLLER);
    }

    private static boolean isFile(URI uri) {
        String filenameExtension = StringUtils.getFilenameExtension(uri.getPath());
        return filenameExtension != null;
    }

    public static void serve(DataOutputStream dos, HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        ADVICES.forEach(advice -> advice.before(httpRequest, httpResponse));
        Controller controller = RequestMappingHandler.getController(httpRequest.getRequestLine().getUri());
        controller.serve(httpRequest, httpResponse);
        ADVICES.forEach(advice -> advice.after(httpRequest, httpResponse));
        httpResponse.respond(dos);
    }
}
