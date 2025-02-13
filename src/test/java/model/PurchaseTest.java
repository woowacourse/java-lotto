package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PurchaseTest {

    @Test
    public void 로또_구매_개수_테스트() {
        // given - 금액이 주어지면
        Integer purchaseAmount = 15000;
        // when
        Purchase purchase = new Purchase(purchaseAmount);
        // then
        assertThat(purchase.size()).isEqualTo(15);
    }

}
