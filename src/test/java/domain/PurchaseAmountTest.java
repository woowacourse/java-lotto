package domain;

import Lotto.domain.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountTest {
    @ParameterizedTest
    @DisplayName("구입 금액이 올바른 범위 안에 있을 때")
    @ValueSource(ints = {1, 25, 500000, 39939})
    void rightInput(int input) {
        PurchaseAmount purchaseAmount = new PurchaseAmount(input);
        assertThat(purchaseAmount).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("구입 금액이 0보다 작을 때")
    @ValueSource(ints = {-1, 0})
    void inputNotNaturalNumber(int input) {
        assertThatThrownBy(() -> new PurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0보다");
    }
}
