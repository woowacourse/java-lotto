package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {
    @Test
    void null_check() {
        assertThrows(NullPointerException.class, () -> {
            LottoNumber.createLottoNumber(null);
        });
    }

    @Test
    void 번호_범위() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber.createLottoNumber(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber.createLottoNumber(46);
        });
    }
}