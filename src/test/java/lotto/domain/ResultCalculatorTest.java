package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCalculatorTest {
    @Test
    @DisplayName("수익률 계산 테스트")
    void calculateEarningRate() {
        Rank.findRank(3, false);
        PurchaseAmount purchaseAmount = PurchaseAmount.calculate(1000);
        assertThat(ResultCalculator.calculateEarningRate(purchaseAmount)).isEqualTo(400);
    }
}
