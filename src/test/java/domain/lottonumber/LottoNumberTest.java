package domain.lottonumber;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {
    @Test
    void 숫자가_1보다_작으면_예외를_던지는지_테스트() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.valueOf(0));
        assertDoesNotThrow(() -> LottoNumber.valueOf(1));
    }

    @Test
    void 숫자가_45보다_크면_예외를_던지는지_테스트() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.valueOf(46));
        assertDoesNotThrow(() -> LottoNumber.valueOf(45));
    }

    @Test
    void 값이_동일한_경우_같은_숫자가_되는지_테스트() {
        assertThat(LottoNumber.valueOf(1)).isEqualTo(LottoNumber.valueOf(1));
    }

    @Test
    void 로또_숫자를_캐싱하는지_테스트() {
        assertThat(LottoNumber.valueOf(1) == LottoNumber.valueOf(1)).isTrue();
    }

    @Test
    void 숫자에_맞는_로또_숫자를_제대로_되돌려주는지_테스트() {
        LottoNumber lottoNumber1 = LottoNumber.valueOf(1);
        LottoNumber lottoNumber45 = LottoNumber.valueOf(45);

        assertThat(lottoNumber1.isOf(1)).isTrue();
        assertThat(lottoNumber45.isOf(45)).isTrue();
    }
}

