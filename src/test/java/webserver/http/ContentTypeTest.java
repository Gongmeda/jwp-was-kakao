package webserver.http;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ContentTypeTest {

    @DisplayName("모르는 확장자의 파일을 파싱하면 Octet-Stream으로 반환한다")
    @Test
    void parse_unknown_extension() {
        ContentType contentType = ContentType.parse("path/file.dino");
        assertThat(contentType).isEqualTo(ContentType.OCTET_STREAM);
    }

    @DisplayName("확장자가 없는 파일을 파싱하면 Octet-Stream으로 반환한다")
    @Test
    void parse_no_extension() {
        ContentType contentType = ContentType.parse("path/file");
        assertThat(contentType).isEqualTo(ContentType.OCTET_STREAM);
    }

    @DisplayName("아는 확장자의 파일을 파싱하면 올바른 타입을 반환한다")
    @Test
    void parse_known_extension() {
        ContentType contentType = ContentType.parse("path/file.html");
        assertThat(contentType).isEqualTo(ContentType.HTML);
    }
}