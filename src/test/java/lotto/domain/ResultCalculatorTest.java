package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCalculatorTest {
    @Test
    void 순위_계산_테스트() {
        ResultCalculator.calculateRank(6, false);
        assertThat(Rank.FIRST_RANK.count)
                .isEqualTo(1);
    }

    @Test
    void 수익률_계산_테스트() {
        ResultCalculator.calculateRank(3, false);
        PurchaseAmount purchaseAmount = PurchaseAmount.calculate(1000);
        assertThat(ResultCalculator.calculateEarningRate(purchaseAmount)).isEqualTo(400);
    }
}
