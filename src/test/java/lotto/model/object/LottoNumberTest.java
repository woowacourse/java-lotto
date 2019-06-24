package lotto.model.object;

<<<<<<< HEAD
import lotto.model.LottoNumber;
=======
>>>>>>> da7b5280162c03e3cb85956a36ace4e4eaa36719
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