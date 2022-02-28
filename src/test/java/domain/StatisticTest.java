package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StatisticTest {

    @Test
    @DisplayName("수익률이 일치하는지 테스트")
    public void getProfitRate() {
        double realProfitRate = 10.5;
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        result.put(Rank.FOURTH, 2);
        result.put(Rank.FIFTH, 1);

        Statistic statistic = new Statistic(result);
        Money money = new Money(10_000);
        double profitRate = statistic.getProfitRate(money);

        assertThat(profitRate).isEqualTo(realProfitRate);
    }

    @Test
    @DisplayName("해당 순위가 당첨되었을 때 통계에 들어갔는지 테스트")
    public void addTest() {

        Statistic statistic = Statistic.valueOf(List.of(Rank.FIFTH));

        assertThat(statistic.getStatistics().get(Rank.FIFTH)).isEqualTo(1);
    }
}
