package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("금액을 올바르게 입력받는지")
    void Generate_Money() {
        Money money = new Money("10000");
        assertThat(money.getInitialMoney()).isEqualTo(10000);
    }

    @Test
    @DisplayName("숫자가 아닌 입력에 대한 예외처리")
    void Not_Number() {
        assertThatThrownBy(() -> {
            Money money = new Money("a");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1000원 미만인 입력 금액에 대한 예외처리")
    void Under_1000() {
        assertThatThrownBy(() -> {
            Money money = new Money("800");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("거스름돈이 발생하는 경우에 대한 예외처리")
    void Not_Divided_By_1000() {
        assertThatThrownBy(() -> {
            Money money = new Money("2500");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
