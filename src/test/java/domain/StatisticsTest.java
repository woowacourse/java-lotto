package domain;

import domain.money.PurchaseAmount;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class StatisticsTest {
    @Test
    void 총_수익률을_제대로_계산하는지_테스트() {
        /* Given : */
        Statistics statistics;
        Map<Rank, CountOfRank> countsOfRank = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            countsOfRank.put(rank, new CountOfRank());
        }
        countsOfRank.get(Rank.FIFTH).countUp();
        statistics = Statistics.of(countsOfRank, PurchaseAmount.valueOf(1000));

        /* Then : */
        assertThat(statistics.calculateEarningRates()).isEqualTo(5, offset(0.00099));
    }
}
