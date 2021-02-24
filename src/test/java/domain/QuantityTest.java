package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuantityTest {
    @DisplayName("수동으로 구매할 로또 수와 자동으로 구매할 로또 수를 Quantity 객체로 포장한다.")
    @Test
    public void calculateQuantity() {
        Quantity lottoPurchase = new Quantity(4, 6);

        assertThat(lottoPurchase.manual()).isEqualTo(4);
        assertThat(lottoPurchase.auto()).isEqualTo(6);
    }

    @DisplayName("수동으로 구매할 로또 수가 음수인 경우 예외를 발생시킨다.")
    @Test
    public void calculateManualCountNegativeException() {
        assertThatThrownBy(() -> {
            new Quantity(-2, 8);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
