package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.ManualLottoGeneratorTest.createCustomLotto;
import static lotto.domain.WinningLottoTest.createCustomWinningLotto;
import static org.assertj.core.api.Assertions.assertThat;

class LottoStatisticsTest {
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = createCustomWinningLotto("1, 2, 3, 4, 5, 6", "20");
    }

    @DisplayName("결과 값을 통계 리스트로 반환")
    @Test
    void getWinCountByRank() {
        Lotto lotto1 = createCustomLotto("1, 2, 3, 20, 21, 40"); // FIFTH
        Lotto lotto2 = createCustomLotto("1, 2, 3, 4, 5, 20"); // SECOND
        Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2));

        List<Rank> ranks = lottos.getResults(winningLotto);
        LottoStatistics lottoStatistics = new LottoStatistics(ranks, new Money(2999));

        List<Integer> numberOfWinByRank = lottoStatistics.getWinCountByRank();
        assertThat(numberOfWinByRank).isEqualTo(Arrays.asList(1, 0, 0, 1, 0));
    }

    @DisplayName("총 수익률 계산")
    @Test
    void getTotalProfit() {
        Lotto lotto1 = createCustomLotto("1, 2, 3, 20, 21, 40"); // FIFTH
        Lotto lotto2 = createCustomLotto("1, 2, 3, 4, 5, 20"); // SECOND
        Lotto lotto3 = createCustomLotto("1, 2, 3, 4, 5, 20"); // SECOND
        Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2, lotto3));

        List<Rank> ranks = lottos.getResults(winningLotto);
        LottoStatistics lottoStatistics = new LottoStatistics(ranks, new Money(3999));

        assertThat(lottoStatistics.getProfitRate()).isEqualTo((float) 60_005_000 / 3000);
    }
}