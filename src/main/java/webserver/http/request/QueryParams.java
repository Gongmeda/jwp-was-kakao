package webserver.http.request;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class QueryParams {

    private static final QueryParams EMTPY_QUERYPARAMS = new QueryParams(Collections.emptyMap());

    private final Map<String, String> map;

    public static QueryParams empty() {
        return EMTPY_QUERYPARAMS;
    }

    private QueryParams(Map<String, String> map) {
        this.map = Collections.unmodifiableMap(map);
    }

    public static QueryParams parse(String text) {
        if (text == null || text.isEmpty()) {
            return empty();
        }

        Map<String, String> map = new HashMap<>();
        for (String line : text.split("&")) {
            String[] tokens = line.split("=");
            map.put(tokens[0], tokens[1]);
        }
        return new QueryParams(map);
    }

    public String get(String key) {
        return map.get(key);
    }
}
