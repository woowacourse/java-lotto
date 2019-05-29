package lotto.domain;

import lotto.domain.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void 번호_10번이_10을_가지고_있는지_테스트() {
        assertThat(LottoNumber.getLottoNumber(10)).isEqualTo(LottoNumber.getLottoNumber(10));
    }

    @Test
    void lotto숫자가_1보다_작은_경우() {
        assertThrows(InvalidLottoNumberException.class, () -> LottoNumber.getLottoNumber(0));
    }

    @Test
    void lotto숫자가_45보다_큰_경우() {
        assertThrows(InvalidLottoNumberException.class, () -> LottoNumber.getLottoNumber(46));
    }
}
