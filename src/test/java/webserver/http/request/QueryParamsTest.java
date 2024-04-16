package webserver.http.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueryParamsTest {

    @DisplayName("쿼리스트링을 파싱할 수 있다")
    @Test
    void parse() {
        String query = "userId=cu&password=password&name=%EC%9D%B4%EB%8F%99%EA%B7%9C&email=brainbackdoor%40gmail.com";
        QueryParams queryParams = QueryParams.parse(query);
        assertAll(
            () -> assertThat(queryParams.get("userId")).isEqualTo("cu"),
            () -> assertThat(queryParams.get("password")).isEqualTo("password"),
            () -> assertThat(queryParams.get("name")).isEqualTo("%EC%9D%B4%EB%8F%99%EA%B7%9C"),
            () -> assertThat(queryParams.get("email")).isEqualTo("brainbackdoor%40gmail.com")
        );
    }

    @DisplayName("null을 파싱하면 빈 쿼리스트링을 반환한다")
    @Test
    void parse_null() {
        String query = null;
        QueryParams queryParams = QueryParams.parse(query);
        assertThat(queryParams).isEqualTo(QueryParams.empty());
    }

    @DisplayName("빈 문자열을 파싱하면 빈 쿼리스트링을 반환한다")
    @Test
    void parse_empty() {
        String query = "";
        QueryParams queryParams = QueryParams.parse(query);
        assertThat(queryParams).isEqualTo(QueryParams.empty());
    }
}