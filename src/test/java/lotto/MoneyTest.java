package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @Test
    @DisplayName("정상적으로 생성된다")
    public void createMoney() {
        Money money = new Money(1000);
        assertThat(money).isEqualTo(new Money(1000));
    }

    @Test
    @DisplayName("1000원 미만은 받을 수 없다.")
    public void notEnoughBudgetTest() {
        assertThatThrownBy(() -> {
           new Money(500);
        }).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("1000원 이상 입력해주세요.");
    }
}
