package lotto.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankStatisticTest {

    private LottosFactory lottosFactory;
    private WinningNumbers winningNumbers;
    private RankStatistic rankStatistic;

    @BeforeEach
    void setUp() {
        List<Lotto> lottos = LottosFactory.generate(List.of(Lotto.generateByManual(List.of(1,2,3,4,5,6)), Lotto.generateByManual(List.of(1,2,3,14,15,16))), 0);
        winningNumbers = WinningNumbers.generate(List.of(1,2,3,4,5,7), 6);
        rankStatistic = new RankStatistic(lottos, winningNumbers);
    }

    @Test
    @DisplayName("당첨된 총 금액 반환")
    void getTotalWinningPrize() {
        Assertions.assertThat(rankStatistic.getTotalWinningPrize()).isEqualTo(30005000);
    }

    @Test
    @DisplayName("특정 등수가 몇번 당첨 되었는지 반환")
    void getCount() {
        Assertions.assertThat(rankStatistic.getCount(Rank.RANK_2)).isEqualTo(1);
        Assertions.assertThat(rankStatistic.getCount(Rank.RANK_5)).isEqualTo(1);
    }
}
