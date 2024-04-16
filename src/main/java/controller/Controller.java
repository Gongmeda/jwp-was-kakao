package controller;

import webserver.http.HttpMethod;
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

        httpResponse.methodNotAllowed();
    }

    protected void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.methodNotAllowed();
    }

    protected void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.methodNotAllowed();
    }
}
