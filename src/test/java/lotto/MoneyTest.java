package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {

    @Test
    @DisplayName("정상적으로 생성된다")
    public void createMoney() {
        Money money = new Money(1000);
        assertThat(money).isEqualTo(new Money(1000));
    }
}
