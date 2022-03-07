package lotto.util;

import lotto.exception.InputTypeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @ParameterizedTest(name = "정수가 아닐 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"1.2", "test"})
    void toIntForOneError(String input) {
        Assertions.assertThatThrownBy(() -> InputValidator.checkInteger(input))
                .isInstanceOf(InputTypeException.class)
                .hasMessage("정수를 입력해주세요.");
    }
}