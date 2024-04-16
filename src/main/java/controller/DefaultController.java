package controller;

import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public class DefaultController extends Controller {

    @Override
    protected void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.notFound();
    }

    @Override
    protected void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        httpResponse.notFound();
    }
}
