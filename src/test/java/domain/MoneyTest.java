package domain;

import Lotto.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {
    @Test
    @DisplayName("구입 금액이 옳게 입력된 것 확인 테스트")
    void rightInputTest() {
        Money money = new Money("10000");
        assertThat(money).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("구입 금액이 정수가 아닐 때")
    @ValueSource(strings = {" ", "n", "3.3"})
    void inputNotNumber(String input) {
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자");
    }

    @Test
    @DisplayName("구입 금액이 음수일 때")
    void inputNotNaturalNumber() {
        assertThatThrownBy(() -> new Money("-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("0보다");
    }
}
