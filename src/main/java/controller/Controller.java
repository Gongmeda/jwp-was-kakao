package controller;

import webserver.http.HttpMethod;
import webserver.http.HttpStatus;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public abstract class Controller {

    public void service(HttpRequest httpRequest, HttpResponse httpResponse) {
        HttpMethod method = httpRequest.getRequestLine().getMethod();

        if (method == HttpMethod.GET) {
            doGet(httpRequest, httpResponse);
            return;
        }

        if (method == HttpMethod.POST) {
            doPost(httpRequest, httpResponse);
            return;
        }

        httpResponse.statusResponse(HttpStatus.METHOD_NOT_ALLOWED);
    }

    protected void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.statusResponse(HttpStatus.METHOD_NOT_ALLOWED);
    }

    protected void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.statusResponse(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
