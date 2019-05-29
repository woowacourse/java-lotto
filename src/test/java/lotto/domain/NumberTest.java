package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.domain.exceptions.LottoNumberException;
import lotto.domain.lotto.Number;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberTest {
    @Test
    void lotto_number_must_be_over_0() {
        assertThrows(LottoNumberException.class, () -> {
            Number.of(0);
        });
    }

    @Test
    void lotto_number_must_be_under_46() {
        assertThrows(LottoNumberException.class, () -> {
            Number.of(46);
        });
    }
}
