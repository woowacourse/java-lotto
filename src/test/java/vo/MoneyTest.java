package vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
    @Test
    @DisplayName("금액은 1000 이상, 1000단위로 입력해야됨")
    void createInputMoneyTest() {
        // given
        int inputMoney = 1000;

        // when
        Money money = new Money(inputMoney);

        // then
        assertThat(money.getMoney()).isEqualTo(inputMoney);
    }

    @ParameterizedTest(name = "{0}원 입력 시")
    @ValueSource(ints = {999, -1, 0, 1111})
    @DisplayName("잘못된 로또 구입 금액 입력 시, IAE 발생")
    void createInputMoneyWithInvalidMoneyShouldFail(int inputMoney) {
        assertThatThrownBy(() -> new Money(inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("금액은 1000원 이상, 1000원 단위로 입력해주세요");
    }
}
