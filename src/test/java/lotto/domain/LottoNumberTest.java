package lotto.domain;

import lotto.exception.UnexpectedInputRangeException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void 경계값_밖의_로또_번호에_대한_예외() {
        assertThrows(UnexpectedInputRangeException.class, () -> {
            LottoNumber.getInstance(0);
        });
        assertThrows(UnexpectedInputRangeException.class, () -> {
            LottoNumber.getInstance(46);
        });
    }

    @Test
    void 경계값_안의_로또_번호에_대한_예외() {
        assertDoesNotThrow(() -> {
            LottoNumber.getInstance(1);
        });
        assertDoesNotThrow(() -> {
            LottoNumber.getInstance(45);
        });
    }

    @Test
    void 같은_숫자일때_메모리주소_테스트() {
        assertThat(LottoNumber.getInstance(1) == LottoNumber.getInstance(1)).isTrue();
    }

    @Test
    void 다른_숫자일때_메모리주소_테스트() {
        assertThat(LottoNumber.getInstance(1) == LottoNumber.getInstance(2)).isFalse();
    }

    @Test
    void 랜덤으로_6개_뽑히는지_테스트() {
        assertThat(LottoNumber.getRandomNumbers().size()).isEqualTo(6);
    }
}
