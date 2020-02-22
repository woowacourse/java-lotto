package lotto.domain;

import lotto.exceptions.NotAllowedMoneyAmountException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {
    @ParameterizedTest
    @MethodSource("generateMoneyInput")
    void initTest(String moneyInput, boolean expected) {
        assertThatThrownBy(() -> {
            Money money = new Money(moneyInput);
        }).isInstanceOf(NotAllowedMoneyAmountException.class)
                .hasMessageContaining("자연수");
    }

    private static Stream<Arguments> generateMoneyInput() {
        return Stream.of(
                Arguments.of("a", false),
                Arguments.of("-1", false),
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of(" ", false));
    }
}