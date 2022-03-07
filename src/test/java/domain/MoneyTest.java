package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessage;

import static org.assertj.core.api.Assertions.assertThat;
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
    @DisplayName("1000원 단위가 아니면 예외가 발생한다.")
    void create_1000원_단위_아닌경우_예외_테스트() {
        int lessThan1000 = 1100;
        assertThatThrownBy(() -> new Money(lessThan1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.NOT_1000_UNIT);
    }

    @Test
    @DisplayName("금액으로 구매할 수 있는 총 로또 갯수를 잘 반환하는지 확인한다.")
    void calculateTotalLottoCountTest() {
        int amount = 15000;
        Money money = new Money(amount);
        int actual = money.calculatePurchasableLottoCount();
        assertThat(actual).isEqualTo(15);
    }
}