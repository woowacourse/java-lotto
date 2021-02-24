package lottogame.domain.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {

    @Test
    @DisplayName("LottoNumber를 문자로 생성하려 했을 때")
    void lottoNumberValueIsString() {
        String value = "abc";
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = LottoNumber.of(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("LottoNumber를 -1로 생성하려 했을 때")
    void lottoNumberValueIsNegativeNumber() {
        String value = "-1";
        assertThatThrownBy(() -> {
            //LottoNumber lottoNumber = new LottoNumber(value);
            LottoNumber lottoNumber = LottoNumber.of(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("LottoNumber를 1로 생성하려 했을 때")
    void lottoNumberValueIsOne() {
        String value = "1";
        //LottoNumber lottoNumber1 = new LottoNumber(value);
        LottoNumber lottoNumber1 = LottoNumber.of(value);
        assertThat(lottoNumber1).isEqualTo(LottoNumber.of(value));
    }
}
