package http;

import utils.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestParser {

    public static HttpRequest parse(BufferedReader br) throws IOException {
        StartLine startLine = StartLine.of(br.readLine());
        HttpHeaders headers = HttpHeaders.of(parseHeader(br));
        HttpQueryParams queryParams = HttpQueryParams.of(startLine.getPath());
        String body = parseBody(br, headers);

        return new HttpRequest(startLine, headers, queryParams, body);
    }

    private static Map<String, List<String>> parseHeader(BufferedReader br) throws IOException {
        Map<String, List<String>> headerMap = new HashMap<>();
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] parts = line.split(": ", 2);
            if (parts.length == 2) {
                String value = parts[1].trim();
                headerMap.put(parts[0].trim(), List.of(value.split(",")));
            }
        }
        return Collections.unmodifiableMap(headerMap);
    }

    private static String parseBody(BufferedReader br, HttpHeaders headers) throws IOException {
        String key = "Content-Length";
        if (headers.map().containsKey(key)) {
            String body = IOUtils.readData(br, Integer.parseInt(headers.map().get(key).get(0)));
            return URLDecoder.decode(body, StandardCharsets.UTF_8);
        }
        return null;
    }
}
