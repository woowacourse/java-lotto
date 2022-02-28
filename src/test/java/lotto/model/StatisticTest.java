package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StatisticTest {

    private static final Money FIRST_PRIZE = Rank.FIRST.getPrize();
    private static final Money SECOND_PRIZE = Rank.SECOND.getPrize();
    private static final Money THIRD_PRIZE = Rank.THIRD.getPrize();

    @Test
    @DisplayName("당첨결과 통계 테스트")
    void summarizeLottoPrize() {
        Statistic statistic = new Statistic(List.of(Rank.FIRST, Rank.FIRST, Rank.SECOND, Rank.THIRD));

        Money expected = FIRST_PRIZE.multiply(2).plus(SECOND_PRIZE).plus(THIRD_PRIZE);
        BigDecimal actualProfitRate = expected.divide(new Money(4000));
        assertThat(statistic.getProfitRate()).isEqualTo(new ProfitRate(actualProfitRate));
        assertThat(statistic.getCountByRank(Rank.FIRST)).isEqualTo(2);
        assertThat(statistic.getCountByRank(Rank.SECOND)).isEqualTo(1);
        assertThat(statistic.getCountByRank(Rank.THIRD)).isEqualTo(1);
        assertThat(statistic.getCountByRank(Rank.FOURTH)).isEqualTo(0);
        assertThat(statistic.getCountByRank(Rank.FIFTH)).isEqualTo(0);
        assertThat(statistic.getCountByRank(Rank.NOTHING)).isEqualTo(0);
    }

    @Test
    @DisplayName("빈 로또 리스트 통계 테스트")
    void summarizeEmptyLottoList() {
        Statistic statistic = new Statistic(Collections.emptyList());

        assertThat(statistic.getProfitRate()).isEqualTo(new ProfitRate(BigDecimal.ONE));
        assertThat(statistic.getCountByRank(Rank.FIRST)).isEqualTo(0);
        assertThat(statistic.getCountByRank(Rank.SECOND)).isEqualTo(0);
        assertThat(statistic.getCountByRank(Rank.THIRD)).isEqualTo(0);
        assertThat(statistic.getCountByRank(Rank.FOURTH)).isEqualTo(0);
        assertThat(statistic.getCountByRank(Rank.FIFTH)).isEqualTo(0);
        assertThat(statistic.getCountByRank(Rank.NOTHING)).isEqualTo(0);
    }
}
