package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseCountTest {

    @Test
    @DisplayName("수동 구매 로또 수가 총 구매 가능한 로또 수 초과 예외")
    void manuallyPurchasedLottoTicketsExceedsTotalException() {
        Money money = new Money(5000);
        int manualCount = 6;

        assertThatThrownBy(() -> new PurchaseCount(money, manualCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("현재 금액으로 해당 로또 수를 구매할 수 없습니다.");
    }
}
