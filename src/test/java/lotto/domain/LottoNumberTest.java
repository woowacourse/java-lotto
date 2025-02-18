package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1})
    void _1부터_45사이의_숫자가_아닐_경우_예외를_반환한다(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
