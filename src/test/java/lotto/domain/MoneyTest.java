package lotto.domain;

import static lotto.domain.Money.LOTTO_PRICE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.common.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("로또의 가격 단위로 구매 가능하다.")
    @Test
    void availablePerLottoPrice() {
        int maxMoney = Integer.MAX_VALUE / LOTTO_PRICE * LOTTO_PRICE;

        assertThatCode(() -> new Money(maxMoney))
                .doesNotThrowAnyException();
    }

    @DisplayName("거스름돈이 생기거나 0, 음수는 허용하지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1000, 1500})
    void rejectInput(int input) {
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_MONEY_INPUT.getMessage());
    }
}
