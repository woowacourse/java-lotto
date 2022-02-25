package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoTest {
    private Lotto lotto;

    @BeforeEach
    void 로또_번호_생성() {
        lotto = LottoFactory.createLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 로또_번호_6개_일치_검사() {
        WinLotto winLotto = LottoFactory.createWinLotto(
                Arrays.asList(1, 2, 3, 4, 5, 6), LottoNumber.from(10)
        );

        int sameNumber = lotto.countSameNum(winLotto);
        assertThat(sameNumber).isEqualTo(6);
    }

    @Test
    void 로또_보너스_포함_될때_검사() {
        assertThat(lotto.isIn(LottoNumber.from(6)))
                .isTrue();
    }

    @Test
    void 로또_보너스_포함_안될때_검사() {
        assertThat(lotto.isIn(LottoNumber.from(10))).isFalse();
    }
}