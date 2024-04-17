package webserver.http;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CookieStore {

    private static final String COOKIE_SPLITTER = "; ";
    public static final String KEY_VALUE_SPLITTER = "=";

    private List<HttpCookie> cookies;

    public static CookieStore empty() {
        return new CookieStore(new ArrayList<>());
    }

    public static CookieStore parse(String text) {
        if (text == null || text.isEmpty()) {
            return empty();
        }
        List<HttpCookie> cookies = Arrays.stream(text.split(COOKIE_SPLITTER))
            .map(token -> {
                String[] keyValue = token.split(KEY_VALUE_SPLITTER);
                return new HttpCookie(keyValue[0], keyValue[1]);
            }).collect(Collectors.toList());
        return new CookieStore(cookies);
    }

    private CookieStore(List<HttpCookie> cookies) {
        this.cookies = cookies;
    }
}
