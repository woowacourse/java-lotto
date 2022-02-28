package domain;

import static domain.LottoNumber.LOTTO_NUMBER_RANGE_MESSAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static utils.Validator.ERROR_FORMAT_MESSAGE;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("로또 번호가 유효한 경우룰 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "45"})
    void input_lottoNumber_valid(final String number) {
        assertDoesNotThrow(() -> new LottoNumber(number));
    }

    @DisplayName("로또 번호가 숫자가 아니거나 0일 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "0", "-1"})
    void input_lottoNumber_invalid_format(final String number) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumber(number);
        });
        assertEquals(ERROR_FORMAT_MESSAGE, exception.getMessage());
    }

    @DisplayName("로또 번호가 유효한 범위가 아닌 경우 예외를 발생시킨다.")
    @Test
    void input_lottoNumber_invalid_number() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumber("46");
        });
        assertEquals(LOTTO_NUMBER_RANGE_MESSAGE, exception.getMessage());
    }

}
