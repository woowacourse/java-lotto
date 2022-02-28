package lotto.util;

import lotto.exception.InputTypeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @ParameterizedTest(name = "자연수가 아닐 경우 예외 발생 - case : {0}")
    @ValueSource(ints = {0, -300})
    void checkNaturalNumber(int input) {
        Assertions.assertThatThrownBy(() -> InputValidator.checkNaturalNumber(input))
                .isInstanceOf(InputTypeException.class)
                .hasMessage("자연수를 입력해주세요.");
    }

}