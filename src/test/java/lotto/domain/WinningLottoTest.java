package lotto.domain;

import lotto.domain.domainexception.InvalidWinningLottoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningLottoTest {

    private LottoNumber lottoNumber1;
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lottoNumber1 = LottoNumber.generateNumber(1);
        lotto = CustomLottoGenerator.makeLotto("1,2,3,4,5,6".split(","));
    }

    @Test
    void 로또_보너스넘버_중복_테스트() {
        assertThrows(InvalidWinningLottoException.class, () -> new WinningLotto(lotto, lottoNumber1));
    }
}