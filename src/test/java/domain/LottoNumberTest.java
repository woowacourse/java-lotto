package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("로또 번호가 유효한 경우룰 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "45"})
    void input_lottoNumber_valid(final String number) {
        assertDoesNotThrow(() -> LottoNumber.getLottoNumber(number));
    }

    @DisplayName("로또 번호가 숫자가 아니거나 0일 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "0", "-1"})
    void input_lottoNumber_invalid_format(final String number) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber.getLottoNumber(number);
        });
        assertEquals("잘못된 값을 입력하였습니다.", exception.getMessage());
    }

    @DisplayName("로또 번호가 유효한 범위가 아닌 경우 예외를 발생시킨다.")
    @Test
    void input_lottoNumber_invalid_number() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber.getLottoNumber("46");
        });
        assertEquals("로또 번호가 유효한 범위(1-45)가 아닙니다.", exception.getMessage());
    }

}
