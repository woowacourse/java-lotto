package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @Test
    @DisplayName("1000원 미만 금액 예외처리")
    void moneyTest() {
        assertThatThrownBy(() -> new Money(500))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatCode(() -> new Money(1000))
                .doesNotThrowAnyException();
    }
}
