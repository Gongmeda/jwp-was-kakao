package controller.advice;

import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public class SessionControllerAdvice implements ControllerAdvice {

    @Override
    public void before(HttpRequest httpRequest, HttpResponse httpResponse) {
        // TODO
    }

    @Override
    public void after(HttpRequest httpRequest, HttpResponse httpResponse) {}
}
