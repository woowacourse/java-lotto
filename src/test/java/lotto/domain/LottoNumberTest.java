package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {

    @Test
    void 올바른_로또_숫자_검증() {
        assertDoesNotThrow(()->new LottoNumber(1));
        assertDoesNotThrow(()->new LottoNumber(46));
    }

    @Test
    void 올바르지_않은_로또_숫자_검증() {
        assertThrows(InvalidLottoNumberException.class, () -> new LottoNumber(0));
    }

}
