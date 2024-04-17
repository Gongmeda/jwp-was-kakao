package controller.advice;

import java.util.UUID;
import webserver.http.CookieStore;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.http.session.Session;
import webserver.http.session.SessionManager;

public class SessionControllerAdvice implements ControllerAdvice {

    @Override
    public void before(HttpRequest httpRequest, HttpResponse httpResponse) {
        CookieStore requestCookieStore = httpRequest.getHeaders().getCookieStore();
        String sessionId = requestCookieStore.getSessionId(httpRequest.getRequestLine().getUri().getPath());
        if (SessionManager.findSession(sessionId) == null) {
            String newSessionId = UUID.randomUUID().toString();
            SessionManager.add(new Session(newSessionId));
            httpResponse.getHeaders().getCookieStore().setSession(newSessionId);
        }
    }

    @Override
    public void after(HttpRequest httpRequest, HttpResponse httpResponse) {}
}
