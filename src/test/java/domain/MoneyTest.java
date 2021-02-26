package domain;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("구입 금액을 숫자로 관리한다.")
    public void 구입_금액을_숫자로_관리한다() {
        Money money = new Money(1000);
        assertThat(money.getValue()).isEqualTo(1000);
    }
}
