package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MoneyTest {

    @Test
    public void buyLottoTest() {

        assertDoesNotThrow(() -> {
            Money money = new Money(1500, 1);
        });
    }

    @Test
    public void buyLottoExceedMoney() {

        assertThatThrownBy(() -> {
            Money money = new Money(1500, 2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void purchasableLottoCountTest() {
        Money money = new Money(5000, 0);
        assertThat(money.getPurchasableLottoCount()).isEqualTo(5);
    }
}
