package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {

    private static final int TICKET_PRICE = 1000;

    @Test
    @DisplayName("구입 금액을 생성한다")
    void makePurchaseAmount() {
        int amount = 14_000;
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
        assertThat(purchaseAmount.getAmount()).isEqualTo(14_000);
    }

    @Test
    @DisplayName("구입 금액은 1000원 단위로 생성된다")
    void makePurchaseAmount1000() {
        int amount = 14_500;
        PurchaseAmount purchaseAmount = new PurchaseAmount(amount);
        assertThat(purchaseAmount.getAmount()).isEqualTo(14_000);
    }

    @Test
    @DisplayName("구입 금액이 1000원 미만일 경우 예외를 발생시킨다.")
    void throwExceptionWhenInputLessThan1000() {
        int amount = 500;
        assertThatThrownBy(() -> new PurchaseAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입금액은 " + TICKET_PRICE + "원 이상이어야 합니다");
    }

    @Test
    @DisplayName("구입 금액만큼 티켓 개수를 반환한다")
    void testCalcTicketAmount() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(14_500);
        int count = purchaseAmount.calculateTheNumberOfTicket();
        assertThat(count).isEqualTo(14);
    }
}
