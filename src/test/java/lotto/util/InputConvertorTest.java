package lotto.util;

import lotto.exception.InputTypeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputConvertorTest {

    @Test
    @DisplayName("입력값을 자연수로 반환한다.")
    void toNaturalNumber() {
        Assertions.assertThat(InputConvertor.toNaturalNumber("14000")).isEqualTo(14000);
    }

    @ParameterizedTest(name = "입력값이 자연수가 아닐 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"1.2", "test", "-300", "0"})
    void toNaturalNumber(String input) {
        Assertions.assertThatThrownBy(() -> InputConvertor.toNaturalNumber(input))
                .isInstanceOf(InputTypeException.class)
                .hasMessage("자연수를 입력해주세요.");
    }
}
