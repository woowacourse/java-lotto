package lotto.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoneyTest {

    @DisplayName("0 이하의 금액을 입력할 경우 예외가 발생한다.")
    @CsvSource(value = {"-1000", "-12000"})
    @ParameterizedTest
    void createWrongRangeMoney(long amount) {
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알맞은 형식으로 입력해주세요.");
    }

    @DisplayName("천원 단위가 아닌 금액을 입력할 경우 예외가 발생한다.")
    @CsvSource(value = {"1200", "1234", "12345"})
    @ParameterizedTest
    void createWrongUnitMoney(long amount) {
        assertThatThrownBy(() -> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("천원 단위로 입력해주세요.");
    }
}