package lotto.domain.lottoresult;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lotto.domain.WinningLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.LottoRank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultsTest {

    private Lottos lottos1;
    private Lottos lottos2;
    private LottoResults lottoResults;

    @BeforeEach
    void setUp() {
        Lotto userLotto1 = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto userLotto2 = Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 7));
        Lotto userLotto3 = Lotto.of(Arrays.asList(1, 2, 10, 33, 35, 38));
        WinningLotto winningLotto = new WinningLotto(Lotto.of(Arrays.asList(1, 2, 3, 4, 5, 6)), 7);

        lottos1 = Lottos.from(Arrays.asList(userLotto1, userLotto2));
        lottos2 = Lottos.from(Arrays.asList(userLotto3));
        lottoResults = LottoResults.of(lottos1.match(winningLotto), lottos2.match(winningLotto));
    }

    @DisplayName("두 LottoResult를 합쳐도 하나의 당첨 결과가 나와야한다")
    @Test
    void 로또_결과_머지_테스트() {
        assertThat(lottoResults.findNumOfMatchByKey(LottoRank.FIRST)).isEqualTo(1);
        assertThat(lottoResults.findNumOfMatchByKey(LottoRank.SECOND)).isEqualTo(1);
        assertThat(lottoResults.findNumOfMatchByKey(LottoRank.NO_MATCH)).isEqualTo(1);
    }

    @Test
    void 수익률_확인_테스트() {
        int numOfLotto = 3;
        int totalPrize = LottoRank.FIRST.getPrize() + LottoRank.SECOND.getPrize();
        assertThat(lottoResults.getEarningsRate())
            .isEqualTo((double) totalPrize / (double) (numOfLotto * Lotto.LOTTO_PRICE));
    }
}