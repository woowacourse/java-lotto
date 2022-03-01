package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProfitRateTest {

    @Test
    void getProfitRate() {
        Money money = new Money(14000);
        ProfitRate profitRate = new ProfitRate(5000, money);
        Assertions.assertThat(profitRate.toStringProfitRate()).isEqualTo("0.35");
    }
}
