package http.parser;

import org.springframework.util.StringUtils;

import java.net.URLConnection;

public class MimeTypeParser {
    public static String parse(String path) {
        String filename = StringUtils.getFilename(path);
        String mime = URLConnection.guessContentTypeFromName(filename);

        if (mime == null) {
            return "application/octet-stream";
        }
        return mime;
    }
}
