package controller;

import db.DataBase;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.http.request.HttpRequest;
import webserver.http.request.QueryParams;
import webserver.http.response.HttpResponse;
import webserver.http.session.Session;
import webserver.http.session.SessionManager;

public class UserLoginController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Override
    public void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        String body = (String) httpRequest.getBody();
        QueryParams queryParams = QueryParams.parse(URLDecoder.decode(body, StandardCharsets.UTF_8));
        String userId = queryParams.get("userId");
        String password = queryParams.get("password");

        User user = DataBase.findUserById(userId);
        if (user == null || !user.getPassword().equals(password)) {
            httpResponse.redirect("/user/login_failed.html");
            return;
        }

        String path = httpRequest.getRequestLine().getUri().getPath();
        Session session = SessionManager.findSession(httpRequest.getHeaders().getCookieStore().getSessionId(path));
        session.setAttribute("userId", userId);

        httpResponse.redirect("/index.html");
        logger.info("login success: {}", userId);
    }
}
