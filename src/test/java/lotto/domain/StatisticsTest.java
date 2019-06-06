package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class StatisticsTest {
    Statistics statistics;

    @BeforeEach
    void setUp() {
        List<Rank> ranks = Arrays.asList(Rank.FIRST, Rank.MISS, Rank.MISS, Rank.FIRST, Rank.THIRD);
        statistics = new Statistics(ranks);
    }

    @Test
    void 통계_테스트() {
        Map<Rank, Long> result = new HashMap<>();
        result.put(Rank.FIRST, (long) 2);
        result.put(Rank.SECOND, (long) 0);
        result.put(Rank.THIRD, (long) 1);
        result.put(Rank.FOURTH, (long) 0);
        result.put(Rank.FIFTH, (long) 0);
        result.put(Rank.MISS, (long) 2);

        assertThat(statistics.ranksStatistics()).isEqualTo(result);
    }

    @Test
    void 수익율_테스트() {
        assertThat(statistics.returnOfRate()).isEqualTo(800300);
    }
}