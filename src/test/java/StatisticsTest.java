import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsTest {

    @Test
    @DisplayName("수익률이 일치하는지 테스트")
    public void getProfitRate() {
        LinkedHashMap<Rank, Integer> result = new LinkedHashMap<>();
        result.put(Rank.FOURTH, 2);
        result.put(Rank.FIFTH, 1);

        Statistic statistic = new Statistic(result);
        Money money = new Money(10_000);
        double profitRate = statistic.getProfitRate(money);

        assertThat(profitRate).isEqualTo(10.5);
    }
}
