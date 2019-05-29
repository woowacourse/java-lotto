package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.domain.exceptions.LottoNumberException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberTest {
    @Test
    void lotto_number_must_be_over_0() {
        assertThrows(LottoNumberException.class, () -> {
            Number.of(-1);
        });
    }
}
