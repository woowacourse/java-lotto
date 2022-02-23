package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoNumbersTest {

    @Test
    void 로또_번호_6개_일치_검사() {
        LottoNumbers lottoNumbers = new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinLottoNumbers winLottoNumbers = WinLottoNumbers.ofString("1, 2, 3, 4, 5, 6");

        int sameNumber = lottoNumbers.countSameNumber(winLottoNumbers);
        assertThat(sameNumber).isEqualTo(6);
    }
}