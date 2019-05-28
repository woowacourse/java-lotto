package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {
    @Test
    void 로또번호_범위_벗어남1() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            new LottoNumber(0);
        });
    }

    @Test
    void 로또번호_범위_벗어남2() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            new LottoNumber(46);
        });
    }
}