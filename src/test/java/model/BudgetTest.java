package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BudgetTest {

    @Test
    @DisplayName("로또 상품금 더하기 테스트")
    void add() {
        Budget budget = Budget.ZERO;
        Budget actualBudget = budget.add(new Budget(2_000_000_000));
        assertThat(actualBudget).isEqualTo(new Budget(2_000_000_000));
    }

    @Test
    @DisplayName("로또 상품금 곱하기 테스트")
    void multiply() {
        Budget budget = new Budget(2000);
        Budget actualBudget = budget.multiply(4);
        assertThat(actualBudget).isEqualTo(new Budget(8000));
    }

    @Test
    void divide() {
        Budget budget = new Budget(4000);
        BigDecimal shareValue = budget.divide(new Budget(1000));
        assertThat(shareValue).isEqualTo(new BigDecimal("4"));
    }

}
