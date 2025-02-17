package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {

    private PurchaseAmount purchaseAmount;

    @Test
    @DisplayName("구매 수량을 계산한다.")
    void countNumberOfPurchases() {
        purchaseAmount = new PurchaseAmount(15000);
        int quantity = purchaseAmount.countNumberOfPurchases();

        assertThat(quantity).isEqualTo(15);
    }

    @Test
    @DisplayName("구매 금액이 1000원 단위가 아닌 경우, 예외를 발생시킨다.")
    void validateUnitOfThousandWon() {
        assertThatThrownBy(() -> new PurchaseAmount(25670))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구매 금액은 1000원 단위어야 합니다.");
    }
}
