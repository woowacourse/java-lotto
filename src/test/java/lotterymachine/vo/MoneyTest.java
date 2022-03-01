package lotterymachine.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    @DisplayName("당첨 금액을 투입된 금액으로 나눈다.")
    void divide() {
        Money inputMoney = new Money(14000);
        Money winningMoney = new Money(5000);
        double expected = 5000.0 / 14000;
        assertThat(winningMoney.divide(inputMoney)).isEqualTo(expected);
    }
}
