package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchasedLottosTest {
    @Test
    @DisplayName("로또 구매 개수 테스트")
    public void lottoPurchaseCountTest() {
        // given - 금액이 주어지면
        Integer purchaseAmount = 15000;
        // when
        PurchasedLottos purchasedLottos = new PurchasedLottos(purchaseAmount);
        // then
        assertThat(purchasedLottos.size()).isEqualTo(15);
    }

    @Test
    @DisplayName("1000의 배수 판별 테스트")
    public void validateDividableTest() {
        //given
        Integer purchaseAmount = 1501;

        // when & then
        assertThatThrownBy(() -> new PurchasedLottos(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1000의 배수를 입력해주세요.");
    }
}
