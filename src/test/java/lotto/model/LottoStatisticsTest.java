package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoStatisticsTest {

    private List<Lotto> lottoList;

    @BeforeEach
    void init() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 8, 9));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 8, 9));
        Lotto lotto3 = new Lotto(List.of(1, 2, 3, 8, 9, 10));
        lottoList = new ArrayList<>(List.of(lotto1, lotto2, lotto3));
    }

    @Test
    @DisplayName("수익률 계산 테스트")
    void revenueTest() {
        Lottos lottos = new Lottos(lottoList);
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoStatistics statistics = lottos.checkRank(winningLotto);

        assertThat(statistics.findRevenue()).isEqualTo((double)105_000 / 3_000);
    }
}