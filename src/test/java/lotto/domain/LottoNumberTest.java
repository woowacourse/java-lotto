package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void validateNumberLowerThanMinimum() {
        assertThrows(InvalidLottoNumber.class, () -> LottoNumber.get(0));
    }

    @Test
    void validateNumberBiggerThanMaximum() {
        assertThrows(InvalidLottoNumber.class, () -> LottoNumber.get(46));
    }

    @Test
    void checkTwoSameLottoNumberHasSameReference() {
        assertThat(LottoNumber.get(1) == LottoNumber.get(1)).isTrue();
    }
}
