package lotto.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.money.Money.ERROR_MESSAGE_MINIMUM_MONEY;
import static lotto.ticket.Number.ERROR_MESSAGE_INVALID_INPUT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MoneyTest {
    @Test
    @DisplayName("돈 객체 생성")
    void moneyCreate() {
        Money money = new Money("10000");
        assertThat(money).isEqualTo(new Money("10000"));
    }

    @Test
    @DisplayName("숫자가 아닌 경우 예외")
    void moneyNotNumber() {
        assertThatThrownBy(() ->
                new Money("*1223"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_MESSAGE_INVALID_INPUT);
    }

    @Test
    @DisplayName("최소 구입 금액 확인")
    void minimumAmount() {
        assertThatThrownBy(() ->
                new Money("900")
        ).isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE_MINIMUM_MONEY);
    }

    @Test
    @DisplayName("음수인 경우 예외")
    void negativeAmount() {
        assertThatThrownBy(() ->
                new Money("-1")
        ).isInstanceOf(IllegalArgumentException.class).hasMessageContaining(ERROR_MESSAGE_MINIMUM_MONEY);
    }

    @Test
    @DisplayName("수익률 계산")
    void calculateProfit() {
        Money money = new Money("14000");
        assertThat(money.calculateProfit(new Money("5000"))).isEqualTo(0.35);
    }
}
