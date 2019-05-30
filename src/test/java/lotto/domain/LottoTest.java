package lotto.domain;

import lotto.domain.domainexception.InvalidLottoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTest {

    private LottoNumber lottoNumber1;
    private LottoNumber lottoNumber2;
    private LottoNumber lottoNumber3;
    private LottoNumber lottoNumber4;
    private LottoNumber lottoNumber5;
    private LottoNumber lottoNumber6;
    private LottoNumber lottoNumber7;
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lottoNumber1 = LottoNumber.generateNumber(1);
        lottoNumber2 = LottoNumber.generateNumber(2);
        lottoNumber3 = LottoNumber.generateNumber(3);
        lottoNumber4 = LottoNumber.generateNumber(4);
        lottoNumber5 = LottoNumber.generateNumber(5);
        lottoNumber6 = LottoNumber.generateNumber(6);
        lottoNumber7 = LottoNumber.generateNumber(7);
        lotto = CustomLottoGenerator.makeLotto("1,2,3,4,5,6,".split(","));
    }

    @Test
    void 생성_오류_5개의_LottoNumber를_가질때_테스트() {
        assertThrows(InvalidLottoException.class, () -> new Lotto(new HashSet<>(Arrays.asList(lottoNumber1, lottoNumber2, lottoNumber3, lottoNumber4, lottoNumber5))));
    }

    @Test
    void 생성_오류_7개의_LottoNumber를_가질때_테스트() {
        assertThrows(InvalidLottoException.class, () -> new Lotto(new HashSet<>(Arrays.asList(lottoNumber1, lottoNumber2, lottoNumber3, lottoNumber4, lottoNumber5, lottoNumber6, lottoNumber7))));
    }

    @Test
    void 생성_오류_중복된_숫자를_가질때_테스트() {
        assertThrows(InvalidLottoException.class, () -> new Lotto(new HashSet<>(Arrays.asList(lottoNumber1, lottoNumber1, lottoNumber2, lottoNumber3, lottoNumber4, lottoNumber5))));
    }

    @Test
    void 숫자_매칭_테스트() {
        assertThat(lotto.matchNumber(lottoNumber1)).isTrue();
    }

    @Test
    void 로또_매칭_테스트() {
        Lotto testLotto = CustomLottoGenerator.makeLotto("1,2,3,4,5,7".split(","));
        assertThat(lotto.matchNumbers(testLotto)).isEqualTo(5);
    }

}
