package domain;

import java.util.EnumMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticsTest {

    @Test
    @DisplayName("수익률이 일치하는지 테스트")
    public void getProfitRate() {
        EnumMap<Rank, Integer> rankCountMap = new EnumMap<>(Rank.class);
        rankCountMap.put(Rank.FOURTH, 2);
        rankCountMap.put(Rank.FIFTH, 1);

        Statistic statistic = new Statistic(rankCountMap);
        Money money = new Money(10_000);
        double profitRate = statistic.getProfitRate(money);

        assertEquals(profitRate, 10.5);
    }
}
