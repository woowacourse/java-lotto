package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void validateNumberLowerThanMinimum() {
        assertThrows(InvalidLottoNumberException.class, () -> LottoNumber.valueOf(0));
    }

    @Test
    void validateNumberBiggerThanMaximum() {
        assertThrows(InvalidLottoNumberException.class, () -> LottoNumber.valueOf(46));
    }

    @Test
    void checkTwoSameLottoNumberHasSameReference() {
        assertThat(LottoNumber.valueOf(1) == LottoNumber.valueOf(1)).isTrue();
    }

    @Test
    void equalTest() {
        assertThat(LottoNumber.valueOf(1).getNumber()).isEqualTo(1);
    }
}
