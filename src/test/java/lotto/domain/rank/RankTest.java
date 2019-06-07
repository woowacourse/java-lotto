package lotto.domain.rank;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @Test
    void 당첨자_추가하는_테스트() {
        RankResult winners = new RankResult();
        RankResult testWinners = new RankResult();
        winners.addWinner(3, false);
        testWinners.addWinner(3, false);
        System.out.println(winners.toString() + "\n" + testWinners.toString());
        assertThat(winners.toString()).isEqualTo(testWinners.toString());
    }

    @Test
    void 전체_상금_구하는_테스트() {
        RankResult winners = new RankResult();
        winners.addWinner(3, false);
        winners.addWinner(4, false);
        winners.addWinner(6, false);
        assertThat(winners.totalRewardMoney()).isEqualTo(5000 + 50000 + 2000000000);
    }

    @Test
    void 수익률_구하는_테스트() {
        RankResult winners = new RankResult();
        winners.addWinner(3, false);
        assertThat((int) winners.rateOfReturn(14000)).isEqualTo(35);
    }

    @Test
    void 이등_상금_구하는_테스트() {
        RankResult winners = new RankResult();
        winners.addWinner(5, true);
        assertThat(winners.totalRewardMoney()).isEqualTo(30000000);
    }
}
