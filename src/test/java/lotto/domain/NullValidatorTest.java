package lotto.domain;

import lotto.util.NullValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NullValidatorTest {

    @DisplayName("null이 들어왔을 때 예외가 발생하는지 확인")
    @NullSource
    @ParameterizedTest
    void nullExceptionTest(String input) {
        assertThatThrownBy(() -> NullValidator.validateNull(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("null이 들어왔습니다.");
    }
}
