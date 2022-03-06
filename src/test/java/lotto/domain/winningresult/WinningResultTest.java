package lotto.domain.winningresult;

import lotto.domain.matchkind.WinningKind;
import lotto.domain.purchaseamount.TotalPurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WinningResultTest {
    @Test
    @DisplayName("수익률을 반환한다.")
    void getProfitRate_Test() {
        //given
        final Map<WinningKind, Integer> winningNumberByMatchKind = Map.of(
                WinningKind.LOWER_THAN_THREE, 0,
                WinningKind.THREE, 1,
                WinningKind.FOUR, 1,
                WinningKind.FIVE, 1,
                WinningKind.FIVE_BONUS, 1,
                WinningKind.SIX, 1);
        final TotalPurchaseAmount totalPurchaseAmount = new TotalPurchaseAmount.Builder()
                .setTotalAmount(5000)
                .build();
        final WinningResult winningResult = new WinningResult(winningNumberByMatchKind, totalPurchaseAmount);
        final double expected = 2031555000 / (double) 5000;
        //when
        final double actual = winningResult.getProfitRate();
        //then
        assertThat(actual).isEqualTo(expected);
    }
}
