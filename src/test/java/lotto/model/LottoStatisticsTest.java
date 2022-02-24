package lotto.model;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoStatisticsTest {

    @Test
    @DisplayName("로또 순위로 통계를 만든다.")
    public void createStatisticsWithLottoRanks() {
        // given
        List<LottoRank> ranks = List.of(LottoRank.values());
        // when
        LottoStatistics statistics = new LottoStatistics(ranks);
        // then
        Assertions.assertThat(statistics).isNotNull();
    }

    @Test
    @DisplayName("로또 순위 별 개수를 센다.")
    public void countPerRanks() {
        // given
        List<LottoRank> ranks = List.of(LottoRank.FIRST, LottoRank.SECOND, LottoRank.SECOND);
        // when
        LottoStatistics statistics = new LottoStatistics(ranks);
        // then
        Assertions.assertThat(statistics.count(LottoRank.SECOND)).isEqualTo(2L);
    }
    @Test
    @DisplayName("수익률을 계산할 수 있다.")
    public void calculateEarningRates() {
        // given
        Money money = new Money(3000);
        List<LottoRank> ranks = List.of(LottoRank.FIFTH, LottoRank.SIXTH, LottoRank.SIXTH);
        // when
        LottoStatistics statistics = new LottoStatistics(ranks);
        // then
        Assertions.assertThat(statistics.calculateEarningRates(money)).isEqualTo((double)5000 / 3000);
    }
}
