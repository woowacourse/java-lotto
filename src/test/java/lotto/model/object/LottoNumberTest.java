package lotto.model.object;

import lotto.model.LottoNumber;
import lotto.model.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {
        @Test
        void 로또_숫자_범위_검사() {
                assertThrows(InvalidLottoNumberException.class, () -> {
                        LottoNumber.getInstance(46);
                });
        }
}