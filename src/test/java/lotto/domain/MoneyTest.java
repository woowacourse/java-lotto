package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import lotto.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    @DisplayName("양수인지 확인")
    void validatePositiveInt() {
        assertThatThrownBy(() -> new Money(0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Money.NON_POSITIVE_ERROR_MESSAGE);
    }

}
