package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningLottoTest {

    private LottoNumber lottoNumber1;
    private LottoNumber lottoNumber2;
    private LottoNumber lottoNumber3;
    private LottoNumber lottoNumber4;
    private LottoNumber lottoNumber5;
    private LottoNumber lottoNumber6;
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lottoNumber1 = new LottoNumber(1);
        lottoNumber2 = new LottoNumber(2);
        lottoNumber3 = new LottoNumber(3);
        lottoNumber4 = new LottoNumber(4);
        lottoNumber5 = new LottoNumber(5);
        lottoNumber6 = new LottoNumber(6);
        lotto = new Lotto(Arrays.asList(lottoNumber1, lottoNumber2, lottoNumber3, lottoNumber4, lottoNumber5, lottoNumber6));
    }

    @Test
    void 로또_보너스넘버_중복_테스트() {
        assertThrows(InvalidWinningLottoException.class, () -> new WinningLotto(lotto, lottoNumber1));
    }
}