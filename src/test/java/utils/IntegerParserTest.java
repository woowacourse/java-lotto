package utils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class IntegerParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"1dd", "!eef", "6165#"})
    @DisplayName("입력이 숫자가 아닌 문자,기호 등이 들어오면 오류를 발생한다.")
    void parseInteger_NotNumber(String value) {
        assertThatThrownBy(() -> IntegerParser.parseInteger(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력한 값이 숫자가 아닙니다.");
    }
}