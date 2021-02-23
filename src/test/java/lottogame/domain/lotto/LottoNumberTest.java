package lottogame.domain.lotto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoNumberTest {
    @Test
    void 객체_생성() {
        LottoNumber lottoNumber = new LottoNumber(10);
        assertEquals(lottoNumber, new LottoNumber(10));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void 숫자_1과_45_사이의_숫자가_아니면_예외_발생하는지_확인(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
