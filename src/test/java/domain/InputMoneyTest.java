package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputMoneyTest {
    @Test
    @DisplayName("금액은 1000 이상, 1000단위로 입력해야됨")
    void createInputMoneyTest() {
        // given
        int money = 1000;
        InputMoney inputMoney = new InputMoney(money);

        // when & then
        assertThat(inputMoney.getMoney()).isEqualTo(money);
    }

    @ParameterizedTest(name = "1000 보다 작은 숫자 전달 시 IAE 발생 - {0}")
    @ValueSource(ints = {-1000, 0, 1, 999})
    void createInputMoneyWithSmallerThan1000ShouldFail(int input) {
        assertThatThrownBy(() -> new InputMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1000 보다 작은 금액을 입력할 수 없습니다.");
    }

    @ParameterizedTest(name = "1000 으로 나누어 떨어지지 않는 숫자 전달 시 IAE 발생 - {0}")
    @ValueSource(ints = {1500, 2001})
    void createInputMoneyWithNotMultipleOf1000ShouldFail(int input) {
        assertThatThrownBy(() -> new InputMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1000으로 나누어 떨어지지 않는 금액을 입력할 수 없습니다.");
    }
}
