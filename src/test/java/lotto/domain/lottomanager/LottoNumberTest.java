package lotto.domain.lottomanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {

    @Test
    void 번호_범위() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumber(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumber(46);
        });
    }
}