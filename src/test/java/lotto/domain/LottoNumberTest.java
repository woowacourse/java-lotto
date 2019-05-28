package lotto.domain;

import org.junit.jupiter.api.Test;

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
}