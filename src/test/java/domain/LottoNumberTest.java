package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {
    @Test
    void 숫자가_1보다_작으면_예외를_던지는지_테스트() {
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(0));
        assertDoesNotThrow(() -> new LottoNumber(1));
    }

    @Test
    void 숫자가_45보다_크면_예외를_던지는지_테스트() {
        assertThrows(IllegalArgumentException.class, () -> new LottoNumber(46));
        assertDoesNotThrow(() -> new LottoNumber(45));
    }

    @Test
    void 값이_동일한_경우_같은_숫자가_되는지_테스트() {
        assertThat(new LottoNumber(1)).isEqualTo(new LottoNumber(1));
    }
}

