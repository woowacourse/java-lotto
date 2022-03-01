package util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberFormatStringParserTest {

    @ParameterizedTest(name = "입력값 \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {"1klay", "천육백", "hello"})
    @DisplayName("파싱 예외 테스트")
    void checkNullOrEmpty(String text) {
        assertThatThrownBy(() -> NumberFormatStringParser.parse(text))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈 값이나 문자가 올 수 없습니다.");
    }

    @Test
    @DisplayName("정상 파싱 테스트")
    void parseValid() {
        assertThat(NumberFormatStringParser.parse("1234")).isEqualTo(1234);
    }
}
