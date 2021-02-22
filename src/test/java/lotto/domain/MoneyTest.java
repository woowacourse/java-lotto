package lotto.domain;

import lotto.exception.Money.MoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("Money 1000원 미만 생성")
    void moneyCreate() {
        assertThatThrownBy(() -> {
            new Money(999);
        }).isInstanceOf(MoneyException.class);
    }
}