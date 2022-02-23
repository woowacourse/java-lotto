package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumbersTest {
    private LottoNumbers lottoNumbers;

    @BeforeEach
    void 로또_번호_생성(){
        lottoNumbers = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 로또_번호_6개_일치_검사() {
        WinLottoNumbers winLottoNumbers = WinLottoNumbers.of("1, 2, 3, 4, 5, 6", 10);

        int sameNumber = lottoNumbers.countSameNumber(winLottoNumbers);
        assertThat(sameNumber).isEqualTo(6);
    }

    @Test
    void 로또_보너스_포함_될때_검사() {
        assertThat(lottoNumbers.isContainsBonus(new LottoNumber(6)))
                .isTrue();
    }

    @Test
    void 로또_보너스_포함_안될때_검사() {
        assertThat(lottoNumbers.isContainsBonus(new LottoNumber(10))).isFalse();
    }
}