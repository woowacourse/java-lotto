package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BudgetTest {
    private Budget budget;

    @BeforeEach
    public void setUp() {
        this.budget = new Budget(new Money(10_000));
    }

    @Test
    @DisplayName("단가와 개수를 넣으면 살 수 있는지를 boolean으로 리턴한다. - true일 때")
    public void canBuy() {
        boolean canBuy= budget.canAfford(new Money(1_000), 10);
        assertTrue(canBuy);
    }

    @Test
    @DisplayName("단가와 개수를 넣으면 살 수 있는지를 boolean으로 리턴한다. - false일 때")
    public void canNotBuy() {
        boolean canNotBuy= budget.canAfford(new Money(1_000), 11);
        assertFalse(canNotBuy);
    }

    @Test
    @DisplayName("단가와 개수를 넣으면 그만큼의 돈을 차감한다.")
    public void buy() {
        budget.buy(new Money(1_000), 5);
        assertThat(budget.remain()).isEqualTo(new Money(5_000));
    }

    @Test
    @DisplayName("예산 이상의 값어치를 사려고 하면 예외가 발생한다.")
    public void canNotBuyException() {
        assertThatThrownBy(() -> budget.buy(new Money(1_000), 11)).isInstanceOf(IllegalArgumentException.class);
    }

}
