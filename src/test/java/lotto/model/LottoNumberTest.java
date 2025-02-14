package lotto.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberTest {
    @DisplayName("45보다 큰 숫자의 로또 넘버가 생기면 예외가 발생한다.")
    @Test
    void createWrongRangeLottoNumber() {
        assertThatThrownBy(() -> new LottoNumber(46))  // 람다로 예외 발생 코드 전달
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또는 1 이상 45 이하만 가능합니다.");
    }

    @DisplayName("숫자가 같은 두개의 다른 LottoNumber 객체는 같아야한다.")
    @Test
    void createSameLottoNumber() {
        LottoNumber number1 = new LottoNumber(1);
        LottoNumber number2 = new LottoNumber(1);
        assertThat(number1).isEqualTo(number2);
    }
}