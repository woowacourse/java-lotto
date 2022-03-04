package domain;

import domain.player.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessage;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("1000원 미만이면 예외가 발생한다.")
    void create_1000원_미만_예외_테스트() {
        int lessThan1000 = 100;
        assertThatThrownBy(() -> new Money(lessThan1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.NOT_ENOUGH_MONEY);
    }

    @Test
    @DisplayName("현재 잔액으로 수동로또를 구매할 수 있는지 확인하고, 잔액으로 구매할 수 없을 경우 예외를 발생시킵니다.")
    void isEnoughMoney() {
        Money money = new Money(5000);
        assertThatThrownBy(() -> money.isEnoughMoney(6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.NOT_ENOUGH_MONEY);

    }

}