package lotto.domain;

import lotto.exception.IllegalMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @ParameterizedTest
    @ValueSource(strings = {"-1", "a"})
    @DisplayName("숫자인지 검증")
    void validateByFormat(String input) {
        assertThatThrownBy(() -> {
            Money money = new Money(input);
        }).isInstanceOf(IllegalMoneyException.class)
        .hasMessage(input + " : 올바른 형식이 아닙니다.");
    }

    @DisplayName("1000이상의 숫자인지 검증")
    @Test
    void validateByLimit() {
        assertThatThrownBy(() -> {
            Money money = new Money("500");
        }).isInstanceOf(IllegalMoneyException.class)
        .hasMessage("500 : 1000보다 큰 금액을 입력해야 합니다.");
    }
}
