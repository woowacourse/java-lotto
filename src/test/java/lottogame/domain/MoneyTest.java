package lottogame.domain;

import lottogame.utils.InvalidMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @DisplayName("구매 금액이 음수일 경우 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"-14000"})
    void 구매_금액_객체_생성(int input) {
        assertThatThrownBy(() -> {
            new Money(input);
        }).isInstanceOf(InvalidMoneyException.class);
    }
}
