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

    @DisplayName("0원 생성 테스트")
    @Test
    void zeroMoneyTest() {
        assertThatThrownBy(() ->
                new Money(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("1000원 미만 돈 생성 테스트")
    @Test
    void thousandMoneyTest() {
        assertThatThrownBy(() ->
                new Money(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("잔돈 반환 테스트")
    @Test
    void calculateChangeTest() {
        Money money = new Money(3900);
        int actual = money.calculateChange();
        assertThat(actual).isEqualTo(900);
    }
}
