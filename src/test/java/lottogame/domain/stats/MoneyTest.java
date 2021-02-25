package lottogame.domain.stats;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTest {
    @Test
    @DisplayName("같은 값을 가지면 같은 객체인지 확인")
    void of1() {
        Money money = Money.of("14000");
        assertEquals(Money.of("14000"), money);
    }

    @ParameterizedTest
    @DisplayName("1000이상의 정수가 아닌 다른 값을 넣으면 예외 발생하는지 확인")
    @ValueSource(strings = {"1abc", "5가", "-1", "0", "01", "500", "999"})
    void of2(String input) {
        assertThatThrownBy(() -> Money.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 1,000이상이어야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("1000이상의 정수를 넣으면 값이 잘 들어가는지 확인")
    @CsvSource(value = {"1000:1000", "1001:1001", "5000:5000", "10000:10000", "50000:50000"}, delimiter = ':')
    void of3(String actualValue, int expectedValue) {
        Money money = Money.of(actualValue);
        assertEquals(expectedValue, money.value());
    }
}
