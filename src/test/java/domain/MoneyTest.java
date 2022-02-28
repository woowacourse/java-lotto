package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MoneyTest {

    @Test
    public void buyLottoTest() {
        Money money = new Money(1500);
        assertDoesNotThrow(() -> {
            money.buyLotto(1);
        });
    }

    @Test
    public void buyLottoExceedMoney() {
        Money money = new Money(1500);
        assertThatThrownBy(() -> {
            money.buyLotto(2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void purchasableLottoCountTest() {
        Money money = new Money(5000);
        assertThat(money.getPurchasableLottoCount()).isEqualTo(5);
    }
}
