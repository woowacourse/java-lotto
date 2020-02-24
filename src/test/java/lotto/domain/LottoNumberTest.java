package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    private static void assertInvokeExceptionWithMessageWhenCreateWinningNumbers(String input, String message) {
        assertThatThrownBy(() -> LottoNumber.of(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessageEndingWith(message);
    }

    @DisplayName("입력값이 없을 때 예외가 발생하는지 확인")
    @NullAndEmptySource
    @ParameterizedTest
    void emptyValueExceptionTest(String input) {
        assertInvokeExceptionWithMessageWhenCreateWinningNumbers(input, "번호를 입력하지 않으셨습니다.");
    }

    @DisplayName("숫자가 아닌 입력값을 넣었을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"하나", "two", "넷", "v", "2~"})
    void notNumberExceptionTest(String input) {
        assertInvokeExceptionWithMessageWhenCreateWinningNumbers(input, "숫자만 입력하시기 바랍니다.");
    }

    @DisplayName("입력값이 로또 숫자의 범위를 넘었을 때 예외가 발생하는지 확인")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "46", "50"})
    void notInRangeExceptionTest(String input) {
        assertInvokeExceptionWithMessageWhenCreateWinningNumbers(input, "이하의 숫자만 입력 가능합니다.");
    }
}
