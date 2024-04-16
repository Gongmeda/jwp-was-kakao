package webserver.http.request;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class QueryParams {

    private final Map<String, String> map;

    private QueryParams(Map<String, String> map) {
        this.map = Collections.unmodifiableMap(map);
    }

    public static QueryParams parse(String path) {
        String[] paths = path.split("\\?");

        if (paths.length == 1) {
            return new QueryParams(Collections.emptyMap());
        }

        Map<String, String> map = new HashMap<>();
        for (String line : paths[1].split("&")) {
            String[] tokens = line.split("=");
            map.put(tokens[0], tokens[1]);
        }
        return new QueryParams(map);
    }
}
