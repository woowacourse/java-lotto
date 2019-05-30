package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WinnersTest {
    @Test
    void 당첨자_추가하는_테스트() {
        Winners winners = new Winners();
        Winners testWinners = new Winners();
        winners.addWinner(3);
        testWinners.addWinner(3);
        System.out.println(winners.toString() + "\n" + testWinners.toString());
        assertThat(winners.toString()).isEqualTo(testWinners.toString());
    }

    @Test
    void 전체_상금_구하는_테스트() {
        Winners winners = new Winners();
        winners.addWinner(3);
        winners.addWinner(4);
        winners.addWinner(6);
        assertThat(winners.totalRewardMoney()).isEqualTo(5000 + 50000 + 2000000000);
    }

    @Test
    void 수익률_구하는_테스트() {
        Winners winners = new Winners();
        winners.addWinner(3);
        assertThat((int) winners.rateOfReturn(14000)).isEqualTo(35);
    }
}
