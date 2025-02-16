package lotto.domain;

import static lotto.constant.ErrorMessage.OUT_OF_RANGE_LOTTO_NUMBER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.util.NumberGenerator;

class LottoNumberTest {
    @Test
    void 랜덤으로_숫자를_생성한다() {
        NumberGenerator generator = (i, j) -> 3;
        assertThat(LottoNumber.generate(generator)).isEqualTo(new LottoNumber(3));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1})
    void _1부터_45사이의_숫자가_아닐_경우_예외를_반환한다(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(OUT_OF_RANGE_LOTTO_NUMBER.getMessage());
    }
}
