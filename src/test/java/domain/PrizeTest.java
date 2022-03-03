package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrizeTest {

    @DisplayName("각각 로또들의 등수에 따라 받을 수 있는 총 상금의 합을 확인한다.")
    @Test
    void total_prize_correct() {
        SortedMap<RankPrize, Integer> rankCounts = new TreeMap<>();
        rankCounts.put(RankPrize.FOURTH, 1);
        rankCounts.put(RankPrize.SECOND, 1);
        rankCounts.put(RankPrize.THIRD, 1);

        Prize result = Prize.calculatePrize(rankCounts);

        assertThat(result.getPrize()).isEqualTo(31550000);
    }
}
