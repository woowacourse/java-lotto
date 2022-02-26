package validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberValidatorTest {
    private static final String NOT_INTEGER_ERROR_MESSAGE = "입력된 값이 정수가 아닙니다.";
    private static final String LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "로또 번호는 1~45 사이로 입력해주세요.";

    @Test
    @DisplayName("로또 번호에 문자를 잘못 입력 시 IllegalArgumentException 오류를 발생한다.")
    void checkNotIntegerInput() {
        assertThatThrownBy(() -> LottoNumberValidator.validate("abc"))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(NOT_INTEGER_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("로또 번호의 범위가 맞지 않을 시 IllegalArgumentException 오류를 발생한다.")
    void checkNotInRangeInput() {
        assertThatThrownBy(() -> LottoNumberValidator.validate("46"))
                .isInstanceOf(IllegalArgumentException.class).hasMessage(LOTTO_NUMBER_RANGE_ERROR_MESSAGE);
    }
}