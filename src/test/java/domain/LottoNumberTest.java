package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("로또 번호가 유효한 경우룰 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "45"})
    void input_lottoNumber_valid(final String number) {
        assertDoesNotThrow(() -> new LottoNumber(number));
    }

    @DisplayName("로또 번호가 유효하지 않는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "46", "-1"})
    void input_lottoNumber_invalid(final String number) {
        assertThatThrownBy(() -> new LottoNumber(number))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("로또 번호가 유효한 범위");
    }
}
