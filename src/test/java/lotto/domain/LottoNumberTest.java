package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @DisplayName("로또 번호는 1 ~ 45 사이이다.")
    @Test
    void 로또_번호_정상() {
        // given
        int number = 10;

        // when & then
        assertDoesNotThrow(() -> new LottoNumber(number));
    }

    @DisplayName("로또 번호가 1 ~ 45이외인 경우 예외를 던진다.")
    @Test
    void 로또_번호_범위_초과() {
        // given
        int number = 0;

        // when & then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호의 범위는 1 ~ 45 사이입니다.");
    }

    @DisplayName("new LottoNumber(1)과 new LottoNumber(1)은 같아야 한다.")
    @Test
    void 로또_번호_동등() {
        // given
        int number = 1;

        // when
        LottoNumber lottoNumber = new LottoNumber(number);
        LottoNumber targetLottoNumber = new LottoNumber(number);

        // then
        assertThat(lottoNumber).isEqualTo(targetLottoNumber);
    }
}