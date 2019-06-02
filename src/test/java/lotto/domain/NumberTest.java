package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.exceptions.LottoNumberException;
import lotto.domain.lotto.Number;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberTest {
    @Test
    void lotto_number_check_range_0() {
        assertThrows(LottoNumberException.class, () -> {
            Number.of(0);
        });
    }

    @Test
    void lotto_number_check_range_46() {
        assertThrows(LottoNumberException.class, () -> {
            Number.of(46);
        });
    }
}
