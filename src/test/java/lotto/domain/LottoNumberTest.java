package lotto.domain;

import org.junit.jupiter.api.Test;

import lotto.exceptions.InvalidLottoNumberException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void lotto_number_must_be_over_0() {
        assertThrows(InvalidLottoNumberException.class, () -> {
            LottoNumber.of(-1);
        });
    }
}
