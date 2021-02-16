package lotto.domain;

import lotto.utils.InvalidMoneyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTest {
    @Test
    void 객체_생성() {
        Money money = new Money("14000");
        assertThat(money).isEqualTo(new Money("14000"));
    }

    @ParameterizedTest
    @ValueSource (strings = {"4가", "-1000", "-기나"})
    void 잘못된_금액_입력(String input) {
        assertThatThrownBy(() -> {
            new Money(input);
        }).isInstanceOf(InvalidMoneyException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"14000:14", "500:0", "1100:1"}, delimiter = ':')
    void 로또_갯수(String input, int expected) {
        Money money = new Money(input);
        assertEquals(expected, money.lottoQuantity());
    }
}
