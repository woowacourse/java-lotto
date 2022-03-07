package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PurchaseCountTest {
    public static final int LOTTO_PRICE = 1000;

    @DisplayName("구매할 수 있는 수량보다 수동 구매 수량이 많으면 예외가 발생된다")
    @Test
    void error_bigger_than_total() {
        int count = 3;
        int manualCount = 4;
        Money money = Money.from(String.valueOf(count * LOTTO_PRICE));

        assertThatThrownBy(() -> PurchaseCount.of(money, String.valueOf(manualCount)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구매할 수 있는 수량을 초과했습니다");
    }

    @DisplayName("수동 구매 수량을 음수로 입력하면 예외가 발생한다")
    @Test
    void error_negative() {
        int count = 10;
        int manualCount = -3;
        Money money = Money.from(String.valueOf(count * LOTTO_PRICE));

        assertThatThrownBy(() -> PurchaseCount.of(money, String.valueOf(manualCount)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구매 수량은 0보다 작을 수 없습니다");
    }

    @DisplayName("10장 중 3장이 수동이면 자동 로또는 7장이다")
    @Test
    void count_10_manual_3() {
        int count = 10;
        int manualCount = 3;
        PurchaseCount purchaseCount = PurchaseCount.of(
                Money.from(String.valueOf(count * LOTTO_PRICE)), String.valueOf(manualCount));

        assertThat(purchaseCount.isTotal(7)).isTrue();
    }
}
