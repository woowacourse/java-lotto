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
        assertThat(winners).isEqualTo(testWinners);
    }
}
