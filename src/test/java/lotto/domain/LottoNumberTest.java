package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.constant.ErrorMessage.ERROR_LOTTO_NUMBER_WRONG_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @Test
    @DisplayName("같은 숫자인 로또 번호는 동일하다")
    void testLottoNumberEqual() {
        int number = 5;
        LottoNumber lottoNumber1 = new LottoNumber(number);
        LottoNumber lottoNumber2 = new LottoNumber(number);

        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }

    @ParameterizedTest(name = "[{index}] 숫자 {0}")
    @ValueSource(ints = {-1, 0, 46})
    @DisplayName("로또 번호가 1 ~ 45 사이의 숫자가 아닌 경우 예외를 발생시킨다")
    void throwExceptionWhenInvalidRange(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_LOTTO_NUMBER_WRONG_RANGE.message());
    }
}
