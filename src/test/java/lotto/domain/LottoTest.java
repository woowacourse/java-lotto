package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTest {

    private LottoNumber lottoNumber1 = new LottoNumber(1);
    private LottoNumber lottoNumber2 = new LottoNumber(2);
    private LottoNumber lottoNumber3 = new LottoNumber(3);
    private LottoNumber lottoNumber4 = new LottoNumber(4);
    private LottoNumber lottoNumber5 = new LottoNumber(5);
    private LottoNumber lottoNumber6 = new LottoNumber(6);
    private LottoNumber lottoNumber7 = new LottoNumber(6);

    @BeforeEach
    void setUp() {
        lottoNumber1 = new LottoNumber(1);
        lottoNumber2 = new LottoNumber(2);
        lottoNumber3 = new LottoNumber(3);
        lottoNumber4 = new LottoNumber(4);
        lottoNumber5 = new LottoNumber(5);
        lottoNumber6 = new LottoNumber(6);
    }

    @Test
    void 생성_오류_5개의_LottoNumber를_가질때_테스트() {
        assertThrows(InvalidLottoException.class, () -> new Lotto(Arrays.asList(lottoNumber1, lottoNumber2, lottoNumber3, lottoNumber4, lottoNumber5)));
    }

    @Test
    void 생성_오류_7개의_LottoNumber를_가질때_테스트() {
        assertThrows(InvalidLottoException.class, () -> new Lotto(Arrays.asList(lottoNumber1, lottoNumber2, lottoNumber3, lottoNumber4, lottoNumber5, lottoNumber6, lottoNumber7)));
    }

    @Test
    void 생성_오류_중복된_숫자를_가질때_테스트() {
        assertThrows(InvalidLottoException.class, () -> new Lotto(Arrays.asList(lottoNumber1, lottoNumber1, lottoNumber2, lottoNumber3, lottoNumber4, lottoNumber5)));
    }
}
