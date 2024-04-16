package webserver.http;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HttpHeadersTest {

    private final String headerString = "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n"
                                        + "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7\n"
                                        + "Accept-Encoding: gzip,deflate\n"
                                        + "Accept-Language: en-us,en;q=0.5\n"
                                        + "Cache-Control: no-cache\n"
                                        + "Connection: keep-alive\n"
                                        + "Host: code.tutsplus.com\n"
                                        + "Keep-Alive: 300\n"
                                        + "Pragma: no-cache\n"
                                        + "User-Agent: Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5 (.NET CLR 3.5.30729)";

    @DisplayName("HTTP 요청/응답 형식의 문자열로 변환할 수 있다")
    @Test
    void toString_convert() {
        HttpHeaders headers = HttpHeaders.parse(headerString);
        assertThat(headers.toString()).isEqualTo(headerString);
    }

    @DisplayName("문자열을 파싱해서 헤더를 반환한다")
    @Test
    void parse() {
        HttpHeaders headers = HttpHeaders.parse(headerString);
        assertAll(
            () -> assertThat(headers.get("Keep-Alive")).isEqualTo(List.of("300")),
            () -> assertThat(headers.get("Accept-Encoding")).isEqualTo(List.of("gzip", "deflate"))
        );
    }

    @DisplayName("null을 파싱하면 빈 헤더를 반환한다")
    @Test
    void parse_null() {
        HttpHeaders headers = HttpHeaders.parse(null);
        assertThat(headers).isEqualTo(HttpHeaders.empty());
    }

    @DisplayName("빈 문자열을 파싱하면 빈 헤더를 반환한다")
    @Test
    void parse_empty() {
        HttpHeaders headers = HttpHeaders.parse("");
        assertThat(headers).isEqualTo(HttpHeaders.empty());
    }
}