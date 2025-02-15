package lotto.domain;

import static lotto.domain.Money.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.common.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @Test
    void 돈은_천원_단위이다() {
        int maxMoney = Integer.MAX_VALUE / LOTTO_PRICE * LOTTO_PRICE;

        assertThatCode(() -> new Money(maxMoney))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    void 음수나_0은_허용하지_않는다(int input) {
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_MONEY_INPUT.getMessage());
    }
}
