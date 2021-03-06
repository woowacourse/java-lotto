package lotto.money;

import lotto.ranking.Ranking;
import lotto.ranking.Statistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PrizeMoneyTest {

    @Test
    @DisplayName("당첨 금액 생성 확인")
    void prizeMoneyCreate() {
        Statistics statistics = new Statistics();
        statistics.calculateStatistics(Arrays.asList(Ranking.FIFTH, Ranking.NOTHING, Ranking.NOTHING, Ranking.NOTHING));
        PrizeMoney prizeMoney = new PrizeMoney(statistics);
        assertThat(prizeMoney).isEqualTo(new PrizeMoney(statistics));
    }

    @Test
    @DisplayName("수익률 계산 확인")
    void calculatePrizeMoney() {
        Statistics statistics = new Statistics();
        statistics.calculateStatistics(Arrays.asList(Ranking.FIFTH, Ranking.NOTHING, Ranking.NOTHING, Ranking.NOTHING));
        PrizeMoney prizeMoney = new PrizeMoney(statistics);
        assertThat(prizeMoney.calculateProfit(new Money("14000"))).isEqualTo(0.35);
    }
}