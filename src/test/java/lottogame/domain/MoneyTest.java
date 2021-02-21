package lottogame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MoneyTest {

    @Test
    @DisplayName("Money에 문자를 넣었을때")
    void moneyValueIsString() {
        assertThatThrownBy(() -> new Money("abc"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Money에 음수를 넣었을때")
    void moneyValueIsNegativeNumber() {
        assertThatThrownBy(() -> new Money("-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Money에 1000원이 있을때 티켓을 구매할수 있다.")
    void buyOnlyOneTicket() {
        Money money = new Money("1000");
        assertThat(money.isCanBuy(1000)).isTrue();
    }
}
