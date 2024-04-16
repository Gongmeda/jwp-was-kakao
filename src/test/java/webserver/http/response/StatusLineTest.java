package webserver.http.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webserver.http.HttpStatus;
import webserver.http.HttpVersion;

class StatusLineTest {

    @DisplayName("HTTP 응답 형식에 맞는 StatusLine 문자열로 변환할 수 있다")
    @Test
    void toString_convert() {
        StatusLine statusLine = new StatusLine(HttpVersion.HTTP_1_1, HttpStatus.OK);
        assertThat(statusLine.toString()).isEqualTo("HTTP/1.1 200 OK");
    }
}