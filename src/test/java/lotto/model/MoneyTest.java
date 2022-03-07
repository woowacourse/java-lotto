package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @DisplayName("돈 생성 테스트")
    @Test
    void MoneyTest() {
        Money money = new Money(10000);
        assertThat(money).isInstanceOf(Money.class);
    }

    @DisplayName("돈 음수 생성 테스트")
    @Test
    void minusMoneyTest() {
        assertThatThrownBy(() ->
                new Money(-2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("돈 더하기 테스트")
    @Test
    void plusMoneyTest() {
        Money money = new Money(0);
        money.plus(new Money(1000));
        assertThat(money.getMoney()).isEqualTo(1000);
    }
}
