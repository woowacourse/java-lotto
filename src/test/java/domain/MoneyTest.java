package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("음수의 금액으로 생성하면 에러를 던지는지 확인한다.")
    void checkNegativeMoney() {
        assertThatThrownBy(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Money.NOT_POSITIVE_ERROR_MESSAGE);
    }
}