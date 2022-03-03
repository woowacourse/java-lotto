package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.NumsGenerator;

@SuppressWarnings("NonAsciiCharacters")
class LottoTest {
    private Lotto lotto;

    @BeforeEach
    void 로또_번호_생성() {
        lotto = LottoFactory.createLotto(NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test
    void 로또_번호_6개_일치_검사() {
        WinNumbers winNumbers = LottoFactory.createWinNums(
                NumsGenerator.generate(Arrays.asList(1, 2, 3, 4, 5, 6)), LottoNumber.getInstance(10)
        );

        int sameNumber = lotto.countSameNum(winNumbers);
        assertThat(sameNumber).isEqualTo(6);
    }

    @Test
    void 로또_보너스_포함_될때_검사() {
        assertThat(lotto.contains(LottoNumber.getInstance(6)))
                .isTrue();
    }

    @Test
    void 로또_보너스_포함_안될때_검사() {
        assertThat(lotto.contains(LottoNumber.getInstance(10))).isFalse();
    }
}