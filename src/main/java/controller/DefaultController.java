package controller;

import webserver.http.HttpStatus;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public class DefaultController extends Controller {

    @Override
    protected void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.statusResponse(HttpStatus.NOT_FOUND);
    }

    @Override
    protected void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.statusResponse(HttpStatus.NOT_FOUND);
    }
}
