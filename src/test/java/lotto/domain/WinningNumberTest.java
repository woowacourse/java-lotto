package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumberTest {

    @Test
    @DisplayName("당첨번호가 중복될 경우 예외를 발생시킨다")
    void throwExceptionWhenDuplicate() {
        String input = "1, 2, 3, 5, 5, 6";

        assertThatThrownBy(() -> new WinningNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 6, 7"})
    @DisplayName("당첨번호의 개수가 6이 아닌 경우 예외를 발생시킨다")
    void throwExceptionWhenInvalidInputSize(String input) {
        assertThatThrownBy(() -> new WinningNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("당첨번호가 empty, blank일 경우 예외를 발생시킨다")
    void throwExceptionWhenInputIsEmptyOrBlank(String input) {
        assertThatThrownBy(() -> new WinningNumber(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨번호가 숫자가 아닌 경우 예외를 발생시킨다")
    void throwExceptionWhenInputIsNotNumber() {
        String input = "notNumber";
        assertThatThrownBy(() -> new WinningNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력값은 숫자여야합니다");
    }
}
