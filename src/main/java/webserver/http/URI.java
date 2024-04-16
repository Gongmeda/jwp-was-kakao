package webserver.http;

import java.util.Objects;
import webserver.http.request.QueryParams;

public class URI {

    private final String path;
    private final QueryParams queryParams;

    public static URI parse(String path) {
        return new URI(path, QueryParams.parse(path));
    }

    private URI(String path, QueryParams queryParams) {
        this.path = path;
        this.queryParams = queryParams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URI uri = (URI) o;
        return Objects.equals(path, uri.path);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(path);
    }
}
