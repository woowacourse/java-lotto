package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @Test
    @DisplayName("로또 번호 범위를 벗어난 경우")
    void numberRange() {
        assertThatThrownBy(() -> new LottoNumber(47))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("사이의 수를 입력해 주세요.");
    }

    @Test
    @DisplayName("동등성 테스트")
    void equalsTest() {
        LottoNumber lottoNumber1 = new LottoNumber(1);
        LottoNumber lottoNumber2 = new LottoNumber(1);

        assertThat(lottoNumber1.equals(lottoNumber2)).isTrue();
    }
}