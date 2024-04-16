package webserver.http;

import webserver.http.request.QueryParams;

public class URI {

    private final String path;
    private final QueryParams queryParams;

    public static URI parse(String rawPath) {
        String[] tokens = rawPath.split("\\?");

        if (tokens.length == 1) {
            return new URI(tokens[0], QueryParams.empty());
        }
        return new URI(tokens[0], QueryParams.parse(tokens[1]));
    }

    private URI(String path, QueryParams queryParams) {
        this.path = path;
        this.queryParams = queryParams;
    }

    public String getPath() {
        return path;
    }

    public QueryParams getQueryParams() {
        return queryParams;
    }
}
