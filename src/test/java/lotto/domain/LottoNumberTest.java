package lotto.domain;

import lotto.domain.errors.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @ParameterizedTest
    @DisplayName("정상적인 숫자일 때")
    @ValueSource(strings = {"1", "45"})
    void validateNumber_right_number(String validNumber) {
        new LottoNumber(validNumber);
    }

    @Test
    @DisplayName("숫자가 범위보다 작을 때")
    void validateNumberScope_less_than_scope() {
        String invalidNumberUnderScope = "0";

        assertThatThrownBy(() -> new LottoNumber(invalidNumberUnderScope))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OVER_SCOPE.getMessage());
    }

    @Test
    @DisplayName("숫자가 범위보다 클_때")
    void validateNumberScope_more_than_scope() {
        String invalidNumberOverScope = "46";

        assertThatThrownBy(() -> new LottoNumber(invalidNumberOverScope))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OVER_SCOPE.getMessage());
    }

    @ParameterizedTest
    @DisplayName("정수로 입력하지 않았을 때")
    @ValueSource(strings = {"천원", "3.2", "ten_dollar"})
    void validateNumber_not_integer(String invalidInputMoney) {
        assertThatThrownBy(() -> new LottoNumber(invalidInputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_NUMBER.getMessage());
    }
}
