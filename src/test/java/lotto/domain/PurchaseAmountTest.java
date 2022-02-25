package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {

    @Test
    @DisplayName("구입 금액을 생성한다")
    void makePurchaseAmount() {
        int amount = 14000;
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
        assertThat(purchaseAmount.getAmount()).isEqualTo(14000);
    }

    @Test
    @DisplayName("구입 금액은 1000원 단위로 생성된다")
    void makePurchaseAmount1000() {
        int amount = 14500;
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
        assertThat(purchaseAmount.getAmount()).isEqualTo(14000);
    }

    @Test
    @DisplayName("구입 금액이 1000원 미만일 경우 예외를 발생시킨다.")
    void throwExceptionWhenInputLessThan1000() {
        int amount = 500;
        assertThatThrownBy(() -> new PurchaseAmount(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액만큼 티켓 개수를 반환한다")
    void testCalcTicketAmount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14500);
        int count = purchaseAmount.calculateTheNumberOfTickets();
        assertThat(count).isEqualTo(14);
    }
}
