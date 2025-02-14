package model;

import static model.ExceptionMessage.INVALID_LOTTO_MIN_PURCHASE;
import static model.ExceptionMessage.INVALID_LOTTO_PURCHASE_UNIT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoPurchaseTest {

    @Test
    @DisplayName("1000원 이하면 예외 처리된다.")
    void inputWithMinPurchase() {
        assertThatThrownBy(() -> LottoPurchase.of(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_MIN_PURCHASE.getMessage(1000));
    }

    @Test
    @DisplayName("1000원 단위가 아니면 예외 처리된다.")
    void inputWithPurchaseUnit() {
        assertThatThrownBy(() -> LottoPurchase.of(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_LOTTO_PURCHASE_UNIT.getMessage(1000));
    }
}
