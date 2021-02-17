package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCalculatorTest {

    @DisplayName("수익률 계산 테스트")
    @Test
    void divide() {
        double profit = LottoCalculator.divide(1000, 5000);
        assertThat(profit).isEqualTo(5);
    }
}
