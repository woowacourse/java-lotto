package lotto.domain.lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {
    @Test
    void 로또번호_범위_벗어남1() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            LottoNumber.of(0);
        });
    }

    @Test
    void 로또번호_범위_벗어남2() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            LottoNumber.of(46);
        });
    }

    @Test
    void 같은_로또번호() {
        assertThat(LottoNumber.of(3)==LottoNumber.of(3)).isTrue();
    }
}