package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseCountTest {

    @Test
    @DisplayName("수동 구매 로또 수가 총 구매 가능한 로또 수 초과 예외")
    void manuallyPurchasedLottoTicketsExceedsTotalException() {
        assertThatThrownBy(() -> PurchaseCount.from(new Money(5000), 6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("현재 금액으로 해당 로또 수를 구매할 수 없습니다.");
    }

    @Test
    @DisplayName("입력 값이 null일 경우 예외")
    void inputNullOnCreationException() {
        assertThatThrownBy(() -> PurchaseCount.from(null, 1))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("입력 값이 null일 수 없습니다.");
    }

    @Test
    @DisplayName("횟수가 음수일 경우 예외")
    void negativeCountException() {
        assertThatThrownBy(() -> PurchaseCount.from(new Money(1000), -13))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("횟수가 음수일 수 없습니다.");
    }
}
