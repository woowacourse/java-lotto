package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitTest {
    @DisplayName("수익률을 계산한다.")
    @Test
    public void calculateProfitTest() {
        Profit profit = new Profit(new Money(10_000), new Money(5_000));

        assertThat(profit.toDouble()).isEqualTo(0.5);
    }
}
