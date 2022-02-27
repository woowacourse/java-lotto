package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    @Test
    @DisplayName("금액에 음수를 입력했을 경우")
    void input_negative() {
        assertThatThrownBy(() -> {
            new Money(-1000);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("금액 추가")
    void add_money() {
        Money money = new Money(0);
        money.add(100);

        assertTrue(money.getMoney() == 100);
    }

    @Test
    @DisplayName("금액 배수로 저장")
    void multiply_money() {
        Money money = new Money(1000);
        money.mulitply(17);

        assertTrue(money.getMoney() == 17000);
    }
}
