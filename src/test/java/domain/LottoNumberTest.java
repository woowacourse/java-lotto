package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberTest {

    @DisplayName("LottoNumber를 생성하는 기능")
    @Test
    void generate() {
        //given
        int bonusBallValue = 7;

        //when
        LottoNumber lottoNumber = new LottoNumber(bonusBallValue);

        //then
        assertThat(lottoNumber).isNotNull();
    }
}