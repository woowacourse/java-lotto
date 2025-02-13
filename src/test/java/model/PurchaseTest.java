package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseTest {

    @Test
    @DisplayName("로또 구매 개수 테스트")
    public void lottoPurchaseCountTest() {
        // given - 금액이 주어지면
        Integer purchaseAmount = 15000;
        // when
        Purchase purchase = new Purchase(purchaseAmount);
        // then
        assertThat(purchase.size()).isEqualTo(15);
    }

}
