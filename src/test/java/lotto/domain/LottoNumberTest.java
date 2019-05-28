package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void validateNumberLowerThanMinimum() {
        assertThrows(InvalidLottoNumber.class, () -> new LottoNumber(0));
    }

    @Test
    void validateNumberBiggerThanMaximum() {
        assertThrows(InvalidLottoNumber.class, () -> new LottoNumber(46));
    }
}
