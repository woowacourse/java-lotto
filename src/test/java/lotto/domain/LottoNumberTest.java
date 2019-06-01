package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {

    @Test
    void 최소_범위보다_작은_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber.getInstance(0);
        });
    }

    @Test
    void 최대_범위보다_큰_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoNumber.getInstance(46);
        });
    }
}
