package lotto.domain;

import lotto.exception.InvalidException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class MoneyTest {

    @ParameterizedTest
    @ValueSource(strings = {"-1", "1050", "abc", "2500"})
    public void 구입금액입력_실패_테스트(String value) {
        assertThatThrownBy(() -> new Money(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InvalidException.ERROR_WRONG_INPUT_MONEY);
    }
}
