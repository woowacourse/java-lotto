package lotto.domain;

import lotto.domain.user.Money;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class MoneyTest {

    @ParameterizedTest
    @ValueSource(strings = {"-1", "1050", "abc", "2500"})
    public void 구입금액입력_실패_테스트(String value) {
        assertThatThrownBy(() -> new Money(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바른 구매 값을 입력해주세요");
    }
}
