package controller;

import db.DataBase;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.http.HttpStatus;
import webserver.http.request.HttpRequest;
import webserver.http.request.QueryParams;
import webserver.http.response.HttpResponse;

public class UserController extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Override
    protected void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
        QueryParams queryParams = QueryParams.parse(httpRequest.getRequestLine().getUri().getQuery());
        createUser(queryParams, httpResponse);
    }

    @Override
    public void doPost(HttpRequest httpRequest, HttpResponse httpResponse) {
        String body = (String) httpRequest.getBody();
        QueryParams queryParams = QueryParams.parse(URLDecoder.decode(body, StandardCharsets.UTF_8));
        createUser(queryParams, httpResponse);
    }

    private void createUser(QueryParams queryParams, HttpResponse httpResponse) {
        String userId = queryParams.get("userId");
        String password = queryParams.get("password");
        String name = queryParams.get("name");
        String email = queryParams.get("email");
        User user = new User(userId, password, name, email);

        DataBase.addUser(user);
        logger.info("add user: {}", user);

        httpResponse.getHeaders().add("Location", "/index.html");
        httpResponse.getStatusLine().setStatus(HttpStatus.FOUND);
    }
}
