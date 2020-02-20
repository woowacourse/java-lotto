package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
    @DisplayName("복권 갯수 계산 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1500,1", "11000,11", "12345,12"}, delimiter = ',')
    public void calculateLottoTicketTest(String input, String expected) {
        Money money = new Money(input);
        Assertions.assertThat(money.toString()).isEqualTo(expected);
    }

    @DisplayName("복권 널값 및 공백, 숫자 이외 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "askjfakl", "0", "123"})
    public void validateMoneyTest(String input) {
        Assertions.assertThatThrownBy(() -> {
            new Money(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
