package lotto.domain;

import lotto.exception.MoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
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