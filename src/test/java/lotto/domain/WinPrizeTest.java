package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WinPrizeTest {
    @Test
    void 수익률_구하기_테스트() {
        long purchasedAmount = 20000;
        long totalPrice = Rank.FIRST.getPrize() + Rank.THIRD.getPrize();
        long actual = totalPrice / purchasedAmount * 100;

        WinPrize winPrize = new WinPrize();
        winPrize.addWinCount(Rank.FIRST);
        winPrize.addWinCount(Rank.THIRD);

        assertThat(actual).isEqualTo(winPrize.getRateOfProfit(purchasedAmount));
    }
}
