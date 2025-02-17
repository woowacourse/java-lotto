package lotto.domain;

import static lotto.domain.LottoNumber.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("로또 번호가 1에서 45 사이라면 정상적으로 객체가 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {LOTTO_RANGE_MINIMUM, LOTTO_RANGE_MAXIMUM})
    void test_LottoNumber(int number) {
        LottoNumber lottoNumber = new LottoNumber(number);

        assertThat(lottoNumber).isNotNull();
    }

    @DisplayName("로또 번호 가 1에서 45 사이가 아니라면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {LOTTO_RANGE_MINIMUM - 1, LOTTO_RANGE_MAXIMUM + 1})
    void testLottoNumber_rangeException(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

}