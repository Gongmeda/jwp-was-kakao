package webserver.http;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class URITest {

    @DisplayName("쿼리스트링이 없는 경로를 파싱할 수 있다")
    @Test
    void parse_withoutQueryString() {
        String path = "/user/create";
        URI uri = URI.parse(path);
        assertAll(
            () -> assertThat(uri.getPath()).isEqualTo(path),
            () -> assertThat(uri.getQuery()).isNull()
        );
    }

    @DisplayName("쿼리스트링이 있는 경로를 파싱할 수 있다")
    @Test
    void parse_withQueryString() {
        String path = "/user/create";
        String query = "userId=cu&password=password&name=%EC%9D%B4%EB%8F%99%EA%B7%9C&email=brainbackdoor%40gmail.com";
        URI uri = URI.parse(path + "?" + query);
        assertAll(
            () -> assertThat(uri.getPath()).isEqualTo(path),
            () -> assertThat(uri.getQuery()).isEqualTo(query)
        );
    }
}