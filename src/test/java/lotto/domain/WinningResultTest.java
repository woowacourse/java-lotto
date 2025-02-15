package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WinningResultTest {

    private final WinningResult winningResult = new WinningResult(Map.of(LottoRank.FIFTH_PLACE, 3));
    private PurchaseAmount purchaseAmount;

    @Test
    @DisplayName("구매 금액보다 당첨 금액이 클 경우, 1보다 큰 수익률을 얻는다.")
    void makeSurplus() {
        purchaseAmount = new PurchaseAmount(5000);

        double actualProfitRate = winningResult.calculateProfitRate(purchaseAmount);
        double expectedProfitRate = 3.0;

        assertThat(actualProfitRate).isEqualTo(expectedProfitRate);
    }

    @Test
    @DisplayName("구매 금액보다 당첨 금액이 작을 경우, 1보다 작은 수익률을 얻는다.")
    void makeDeficit() {
        purchaseAmount = new PurchaseAmount(30000);

        double actualProfitRate = winningResult.calculateProfitRate(purchaseAmount);
        double expectedProfitRate = 0.5;

        assertThat(actualProfitRate).isEqualTo(expectedProfitRate);
    }

}
