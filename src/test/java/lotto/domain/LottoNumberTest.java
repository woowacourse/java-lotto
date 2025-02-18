package lotto.domain;

import static lotto.domain.LottoNumber.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("로또 번호가 1에서 45 사이라면 정상적으로 객체가 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {LOTTO_RANGE_MINIMUM, LOTTO_RANGE_MAXIMUM})
    void test_LottoNumber(int number) {
        LottoNumber lottoNumber = LottoNumber.of(number);

        assertThat(lottoNumber).isNotNull();
    }

    @DisplayName("로또 번호 가 1에서 45 사이가 아니라면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {LOTTO_RANGE_MINIMUM - 1, LOTTO_RANGE_MAXIMUM + 1})
    void testLottoNumber_rangeException(int number) {
        assertThatThrownBy(() -> LottoNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 비교 결과를 검증한다.")
    @Test
    void test_compareTo() {
        LottoNumber number1 = of(1);
        LottoNumber number2 = of(1);
        LottoNumber number3 = of(2);

        assertThat(number1.compareTo(number3)).isNegative();
        assertThat(number3.compareTo(number1)).isPositive();
        assertThat(number1.compareTo(number2)).isZero();
    }

}