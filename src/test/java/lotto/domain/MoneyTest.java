package lotto.domain;

import lotto.exception.IllegalMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "a", "500"})
    @DisplayName("1000 이상의 정수인지 검증")
    void isValidMoney(String input) {
        assertThatThrownBy(() -> {
            Money money = new Money(input);
        }).isInstanceOf(IllegalMoney.class);
    }
}
