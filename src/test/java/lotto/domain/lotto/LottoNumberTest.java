package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @Test
    void generate_valid() {
        assertThatCode(() -> new LottoNumber(1)).doesNotThrowAnyException();
    }

    @Test
    void generate_invalid1() {
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void generate_invalid2() {
        assertThatThrownBy(() -> new LottoNumber(46))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void generate_valid_number() {
        assertThatCode(() -> LottoNumber.valueOf("1"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("앞에 공백 있는 숫자")
    void generate_valid_number2() {
        assertThatCode(() -> LottoNumber.valueOf(" 1"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("뒤에 공백 있는 숫자")
    void generate_valid_number3() {
        assertThatCode(() -> LottoNumber.valueOf("1 "))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("숫자 사이에 공백 있는 경우")
    void generate_valid_number4() {
        assertThatThrownBy(() -> LottoNumber.valueOf("1 2"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void generate_valid_not_int() {
        assertThatThrownBy(() -> LottoNumber.valueOf("0.1"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void generate_invalid_not_number() {
        assertThatThrownBy(() -> LottoNumber.valueOf("word"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void getNumber() {
        LottoNumber lottoNumber = new LottoNumber(1);

        assertThat(lottoNumber.toInt()).isEqualTo(1);
    }

    @Test
    void getNumber2() {
        LottoNumber lottoNumber = new LottoNumber(2);

        assertThat(lottoNumber.toInt()).isEqualTo(2);
    }
}