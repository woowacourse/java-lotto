package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("돈 객체 테스트")
class MoneyTest {
    @ParameterizedTest
    @ValueSource(ints = {1700, -1, 50001})
    @DisplayName("돈 객체 생성자")
    void moneyConstructorTest(int money) {
        assertThatThrownBy(() -> new Money(money))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageEndingWith("만 구매 가능합니다.");
    }
}