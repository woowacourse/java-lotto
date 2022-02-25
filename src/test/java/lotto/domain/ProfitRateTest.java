package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitRateTest {

    @Test
    @DisplayName("투입한 금액과 당첨으로 얻은 수익으로 수익률 계산")
    void getProfitRate() {
        Money money = new Money("14000");
        ProfitRate profitRate = new ProfitRate(5000, money);
        Assertions.assertThat(profitRate.toStringProfitRateUntilSecondDecimal()).isEqualTo("0.35");
    }
}
