package webserver.http;

import java.util.Objects;
import org.springframework.util.StringUtils;

public enum ContentType {
    OCTET_STREAM(null, "application/octet-stream"),
    HTML("html", "text/html;charset=utf-8"),
    CSS("css", "text/css;charset=utf-8"),
    JS("js", "text/javascript;charset=utf-8"),
    ICO("ico", "image/icon"),
    PNG("png", "image/jpeg"),
    TTF("ttf", "font/ttf"),
    WOFF("woff", "font/woff"),
    WOFF2("woff2", "font/woff2");

    private final String extension;
    private final String contentType;

    ContentType(String extension, String contentType) {
        this.extension = extension;
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public static ContentType parse(String path) {
        String extension = StringUtils.getFilenameExtension(path);
        return resolve(extension);
    }

    private static ContentType resolve(String extension) {
        if (extension == null) {
            return OCTET_STREAM;
        }

        for (ContentType type : ContentType.values()) {
            if (Objects.equals(type.extension, extension)) {
                return type;
            }
        }
        return OCTET_STREAM;
    }
}
