package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.properties.LottoProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseAmountTest {

    @DisplayName("로또 가격에 따른 살 수 있는 로또 개수 반환")
    @Test
    void availableLottoQuantityTest() {
        int money = 12300;

        PurchaseAmount purchaseAmount = PurchaseAmount.of(money);
        int quantity = purchaseAmount.calculateAvailableQuantity(LottoProperties.LOTTO_PRICE);

        assertThat(quantity).isEqualTo(12);
    }
}
