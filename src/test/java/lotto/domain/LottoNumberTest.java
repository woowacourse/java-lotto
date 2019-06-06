package lotto.domain;

import lotto.exception.UnexpectedInputRangeException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void 범위밖의_로또_번호에_대한_예외() {
        assertThrows(UnexpectedInputRangeException.class, () -> { LottoNumber.getNumber(0); });
    }

    @Test
    void 같은_숫자일때_메모리주소_테스트() {
        assertThat(LottoNumber.getNumber(1) == LottoNumber.getNumber(1)).isTrue();
    }

    @Test
    void 다른_숫자일때_메모리주소_테스트() {
        assertThat(LottoNumber.getNumber(1) == LottoNumber.getNumber(2)).isFalse();
    }

    @Test
    void 랜덤으로_6개_뽑히는지_테스트() {
        assertThat(LottoNumber.getRandomNumbers().size()).isEqualTo(6);
    }
}
