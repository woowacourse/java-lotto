package lottogame.domain.number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    @DisplayName("LottoNumber를 문자로 생성하려 했을 때")
    void lottoNumberValueIsString() {
        String value = "abc";
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = new LottoNumber(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("LottoNumber를 -1로 생성하려 했을 때")
    void lottoNumberValueIsNegativeNumber(){
        String value = "-1";
        assertThatThrownBy(() -> {
            LottoNumber lottoNumber = new LottoNumber(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("LottoNumber를 1로 생성하려 했을 때")
    void lottoNumberValueIsOne() {
        String value = "1";
        LottoNumber lottoNumber1 = new LottoNumber(value);
        assertThat(lottoNumber1).isEqualTo(new LottoNumber(value));
    }
}
