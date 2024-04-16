package webserver.http;

public class URI {

    private final String path;
    private final String query;

    public static URI parse(String rawPath) {
        String[] tokens = rawPath.split("\\?");

        if (tokens.length == 1) {
            return new URI(tokens[0], null);
        }
        return new URI(tokens[0], tokens[1]);
    }

    private URI(String path, String query) {
        this.path = path;
        this.query = query;
    }

    public String getPath() {
        return path;
    }

    public String getQuery() {
        return query;
    }
}
