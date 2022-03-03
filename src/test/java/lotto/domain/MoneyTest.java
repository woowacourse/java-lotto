package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("금액을 올바르게 입력받는지")
    void Generate_Money() {
        int value = 10000;
        Money money = new Money(value);
        assertThat(money.getAmount()).isEqualTo(value);
    }

    @Test
    @DisplayName("단위 금액 미만인 입력 금액에 대한 예외처리")
    void Under_Unit_Amount() {
        assertThatThrownBy(() -> new Money(800)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("거스름돈이 발생하는 경우에 대한 예외처리")
    void Not_Divided_By_1000() {
        assertThatThrownBy(() -> new Money(2500)).isInstanceOf(IllegalArgumentException.class);
    }
}
