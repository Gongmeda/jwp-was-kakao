package controller.advice;

import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public interface ControllerAdvice {
    void before(HttpRequest httpRequest, HttpResponse httpResponse);
    void after(HttpRequest httpRequest, HttpResponse httpResponse);
}
