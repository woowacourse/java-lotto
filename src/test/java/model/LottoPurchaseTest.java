package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.LottoPurchaseExceptionType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoPurchaseTest {

    @ParameterizedTest(name = "{0}원 이하면 예외 처리된다.")
    @ValueSource(ints = {LottoPurchase.MIN_AMOUNT})
    void inputWithMinPurchase(final int minAmount) {
        assertThatThrownBy(() -> LottoPurchase.of(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LottoPurchaseExceptionType.INVALID_LOTTO_MIN_PURCHASE.getMessage(minAmount));
    }

    @ParameterizedTest(name = "{0}원 단위가 아니면 예외 처리된다.")
    @ValueSource(ints = {LottoPurchase.MIN_AMOUNT})
    void inputWithPurchaseUnit(final int minAmount) {
        assertThatThrownBy(() -> LottoPurchase.of(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LottoPurchaseExceptionType.INVALID_LOTTO_PURCHASE_UNIT.getMessage(minAmount));
    }
}
