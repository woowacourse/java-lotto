package lotto.domain;

import lotto.domain.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void init() {
        assertThat(new LottoNumber(10)).isEqualTo(new LottoNumber(10));
    }

    @Test
    void lotto숫자가_1보다_작은_경우() {
        assertThrows(InvalidLottoNumberException.class, () -> new LottoNumber(0));
    }

    @Test
    void lotto숫자가_45보다_큰_경우() {
        assertThrows(InvalidLottoNumberException.class, () -> new LottoNumber(46));
    }
}
