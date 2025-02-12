package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PurchasedLottosTest {

    @Test
    public void 로또_구매_개수_테스트() {
        // given - 금액이 주어지면
        Integer purchaseAmount = 15000;
        // when
        PurchasedLottos purchasedLottos = new PurchasedLottos(purchaseAmount);
        // then
        assertThat(purchasedLottos.size()).isEqualTo(15);
    }

}
