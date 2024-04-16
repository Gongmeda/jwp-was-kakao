package controller;

import java.io.IOException;
import java.net.URISyntaxException;
import utils.FileIoUtils;
import webserver.http.ContentType;
import webserver.http.URI;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public class ResourceController extends Controller {

    @Override
    protected void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
        URI uri = httpRequest.getRequestLine().getUri();
        if (uri.getPath().endsWith(".html") || uri.getPath().endsWith(".ico")) {
            handleFileRequest("templates", httpRequest, httpResponse);
            return;
        }
        handleFileRequest("static", httpRequest, httpResponse);
    }

    private void handleFileRequest(String parentFolder, HttpRequest request, HttpResponse httpResponse) {
        try {
            String path = request.getRequestLine().getUri().getPath();
            String filePath = parentFolder + path;

            byte[] body = FileIoUtils.loadFileFromClasspath(filePath);
            httpResponse.setBody(body, ContentType.parse(filePath));
        } catch (IOException | URISyntaxException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
}
