package lotto.domain;

import lotto.exception.MoneyOnlyNaturalNumberException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest(name = "자연수가 아닐 경우 예외 발생 - case : {0}")
    @ValueSource(strings = {"1.2", "test", "-300", "0", "0123", "00000"})
    void checkNaturalNumber(String input) {
        Assertions.assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(MoneyOnlyNaturalNumberException.class)
                .hasMessage("구입금액은 자연수만 가능합니다.");
    }
}
