package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lotto.domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

class WinningStatisticsTest {
    private List<Rank> ranks = new ArrayList<>();

    @BeforeEach
    void setup() {
        ranks.add(FIRST);
    }

    @Test
    void getStatisticsTest() {
        WinningStatistics winStat = new WinningStatistics(ranks);
        assertThat(winStat.getStatistics().get(FIRST)).isEqualTo(1);
        assertThat(winStat.getStatistics().get(SECOND)).isEqualTo(0);
    }

    @Test
    void getInterestRateTest() {
        WinningStatistics winStat = new WinningStatistics(Arrays.asList(Rank.FIFTH));
        assertThat(winStat.getInterestRate(new LottoBuyingMoney(1000))).isEqualTo((long) 500);
    }
}