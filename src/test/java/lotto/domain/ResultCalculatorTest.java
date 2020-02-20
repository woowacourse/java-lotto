package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCalculatorTest {
    @Test
    @DisplayName("순위 계산 테스트")
    void calculateRank() {
        ResultCalculator.calculateRank(6, false);
        assertThat(Rank.FIRST_RANK.count)
                .isEqualTo(1);
    }

    @Test
    @DisplayName("수익률 계산 테스트")
    void calculateEarningRate() {
        ResultCalculator.calculateRank(3, false);
        PurchaseAmount purchaseAmount = PurchaseAmount.calculate(1000);
        assertThat(ResultCalculator.calculateEarningRate(purchaseAmount)).isEqualTo(400);
    }
}
