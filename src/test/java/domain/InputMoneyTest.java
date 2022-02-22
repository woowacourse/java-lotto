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

    @ParameterizedTest(name = "{0}원 입력 시, IAE 발생")
    @ValueSource(ints = {999, -1, 0, 1111})
    void createInputMoneyWithInvalidMoneyShouldFail(int money) {
        assertThatThrownBy(() -> new InputMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("금액은 1000원 이상, 1000원 단위로 입력해주세요");
    }
}
