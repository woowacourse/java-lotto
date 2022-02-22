package lotto.domain;

import lotto.exception.MoneyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest(name = "자연수가 아닐 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"1.2", "test", "-300", "0", "0123", "00000"})
    void checkNaturalNumber(String input) {
        Assertions.assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(MoneyException.class)
                .hasMessage("구입금액은 자연수만 가능합니다.");
    }

    @ParameterizedTest(name = "1000원 단위가 아닐 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"100", "1200", "1234"})
    void checkUnit(String input) {
        Assertions.assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(MoneyException.class)
                .hasMessage("구입금액은 1000원 단위만 가능합니다.");
    }
}
