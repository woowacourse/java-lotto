package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberTest {
    @Test
    void 로또_번호가_1미만인_경우() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.valueOf(0));
    }

    @Test
    void 로또_번호가_45초과인_경우() {
        assertThrows(IllegalArgumentException.class, () -> LottoNumber.valueOf(46));
    }
}
