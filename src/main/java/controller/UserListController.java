package controller;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import db.DataBase;
import java.io.IOException;
import java.util.Map;
import webserver.http.ContentType;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.http.session.Session;
import webserver.http.session.SessionManager;

public class UserListController extends Controller {

    @Override
    protected void doGet(HttpRequest httpRequest, HttpResponse httpResponse) {
        String path = httpRequest.getRequestLine().getUri().getPath();
        String sessionId = httpRequest.getHeaders().getCookieStore().getSessionId(path);
        Session session = SessionManager.findSession(sessionId);

        if (session == null) {
            httpResponse.redirect("index.html");
            return;
        }

        String page = generateListPage();

        httpResponse.setBody(page.getBytes(), ContentType.HTML);
    }

    private String generateListPage() {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".html");
        Handlebars handlebars = new Handlebars(loader);

        Template template;
        try {
            template = handlebars.compile("user/list");
            return template.apply(Map.of("users", DataBase.findAll()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
