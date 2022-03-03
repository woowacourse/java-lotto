package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("로또 번호는 1 ~ 45 사이이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20, 45})
    void 로또_번호_정상(int number) {
        // given & when & then
        assertDoesNotThrow(() -> LottoNumber.from(number));
    }

    @DisplayName("로또 번호가 1 ~ 45이외인 경우 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1})
    void 로또_번호_범위_초과(int number) {
        // given & when & then
        assertThatThrownBy(() -> LottoNumber.from(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호의 범위는 1 ~ 45 사이입니다.");
    }

    @DisplayName("new LottoNumber(1)과 new LottoNumber(1)은 같아야 한다.")
    @Test
    void 로또_번호_동등() {
        // given
        int number = 1;

        // when
        LottoNumber lottoNumber = LottoNumber.from(number);
        LottoNumber targetLottoNumber = LottoNumber.from(number);

        // then
        assertThat(lottoNumber).isEqualTo(targetLottoNumber);
    }
}