package lotto.domain;

import lotto.domain.domainexception.InvalidLottoNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumberTest {
    @Test
    void 생성_오류_0_테스트() {
        assertThrows(InvalidLottoNumberException.class, ()-> new LottoNumber(0));
    }

    @Test
    void 생성_오류_46_테스트() {
        assertThrows(InvalidLottoNumberException.class, ()-> new LottoNumber(46));
    }
}
