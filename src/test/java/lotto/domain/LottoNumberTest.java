package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.utils.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @Test
    @DisplayName("성공 - 숫자로 로또넘버 생성")
    void generate_valid() {
        assertThatCode(() -> new LottoNumber(1))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 0이하 숫자로 로또넘버 생성")
    void generate_invalid1() {
        assertThatThrownBy(() -> new LottoNumber(0))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("실패 - 46이상 숫자로 로또넘버 생성")
    void generate_invalid2() {
        assertThatThrownBy(() -> new LottoNumber(46))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("성공 - 문자인 숫자로 로또넘버 생성")
    void generate_valid_number() {
        assertThatCode(() -> new LottoNumber("1"))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("성공 - 앞에 공백 있는 문자")
    void generate_valid_number2() {
        assertThatCode(() -> new LottoNumber(" 1"))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("성공 - 뒤에 공백 있는 숫자")
    void generate_valid_number3() {
        assertThatCode(() -> new LottoNumber("1 "))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 숫자 사이에 공백 있는 경우")
    void generate_valid_number4() {
        assertThatThrownBy(() -> new LottoNumber("1 2"))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("실패 - 정수가 아닌 문자 숫자에 공백 있는 경우")
    void generate_valid_not_int() {
        assertThatThrownBy(() -> new LottoNumber("0.1"))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("실패 - 숫자가 아닌 문자인 경우")
    void generate_invalid_not_number() {
        assertThatThrownBy(() -> new LottoNumber("word"))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("객체 일치 여부 체크")
    void getNumber() {
        LottoNumber lottoNumber = new LottoNumber(1);

        assertThat(lottoNumber.getNumber()).isEqualTo(1);
    }

}