package lotto.domain;

import lotto.exception.MoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("Money 0원 이하 생성")
    void moneyCreate() {
        assertThatThrownBy(() -> {
            new Money(0);
        }).isInstanceOf(MoneyException.class);
    }
}