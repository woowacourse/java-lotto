package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PurchaseTest {
    @DisplayName("수동 구매 개수 * 1000은 자동 구매 개수보다 적어야한다.")
    @Test
    void manualPurchaseCount() {
        Money money = new Money(3000);
        int manualPurchase = 4;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Purchase(money, manualPurchase));
    }
}
