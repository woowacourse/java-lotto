package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        assertThat(money.getMoney()).isEqualTo(100);
    }

    @Test
    @DisplayName("금액 빼기")
    void subtract_money() {
        Money money = new Money(2000);
        money.subtract(1000);

        assertThat(money.getMoney()).isEqualTo(1000);
    }

    @Test
    @DisplayName("금액 배수로 저장")
    void multiply_money() {
        Money money = new Money(1000);
        money.multiply(17);

        assertThat(money.getMoney()).isEqualTo(17000);
    }
}
