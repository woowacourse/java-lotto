package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
    @DisplayName("Money 생성자 테스트")
    @ParameterizedTest
    @ValueSource(ints = {999, -1})
    void moneyConstructorTest(int input) {
        Assertions.assertThatThrownBy(() -> {
            new Money(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
