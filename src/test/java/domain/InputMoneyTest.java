package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("InputMoney 테스트")
public class InputMoneyTest {
    @Test
    @DisplayName("생성자에 1000 이상인 1000단위의 숫자를 전달해야 한다.")
    void createInputMoneyTest() {
        // given
        int money = 1000;
        InputMoney inputMoney = new InputMoney(money);

        // when & then
        assertThat(inputMoney.getMoney()).isEqualTo(money);
    }

    @DisplayName("생성자에 1000 보다 작은 숫자 전달 시 IAE 발생한다.")
    @ParameterizedTest(name = "{0} 전달")
    @ValueSource(ints = {-1000, 0, 1, 999})
    void createInputMoneyWithSmallerThan1000ShouldFail(int input) {
        assertThatThrownBy(() -> new InputMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InputMoney.ERROR_MESSAGE_FOR_MINIMUM_NUMBER);
    }

    @DisplayName("생성자에 1000 으로 나누어 떨어지지 않는 숫자 전달 시 IAE 발생한다.")
    @ParameterizedTest(name = "{0} 전달")
    @ValueSource(ints = {1500, 2001})
    void createInputMoneyWithNotMultipleOf1000ShouldFail(int input) {
        assertThatThrownBy(() -> new InputMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InputMoney.ERROR_MESSAGE_FOR_MULTIPLE_OF_UNIT);
    }
}
