package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuantityTest {
    private Money budget;

    @BeforeEach
    public void setUp() {
        budget = new Money(12_000);
    }

    @DisplayName("수동으로 구매할 로또 수와 자동으로 구매할 로또 수를 Quantity 객체로 포장한다.")
    @Test
    public void calculateQuantity() {
        Quantity quantity = new Quantity(budget, 4);

        assertThat(quantity.manual()).isEqualTo(4);
        assertThat(quantity.auto()).isEqualTo(8);
    }

    @DisplayName("수동으로 구매할 로또 수가 음수인 경우 예외를 발생시킨다.")
    @Test
    public void validatePositiveCountException() {
        assertThatThrownBy(() -> {
            new Quantity(budget, -2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수동으로 구매할 로또 수가 구입금액으로 살 수 있는 최대 개수를 초과한 경우 예외를 발생시킨다.")
    @Test
    public void validateWithinBudgetException() {
        assertThatThrownBy(() -> {
            new Quantity(budget, 16);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
