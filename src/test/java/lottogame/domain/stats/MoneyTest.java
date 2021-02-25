package lottogame.domain.stats;

import lottogame.utils.InvalidMoneyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {
    @Test
    void 객체_생성() {
        Money money = new Money("14000");
        assertThat(money).isEqualTo(new Money("14000"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"4가", "-1000", "-기나"})
    void 잘못된_금액_입력(String input) {
        assertThatThrownBy(() ->
                new Money(input)
        ).isInstanceOf(InvalidMoneyException.class);
    }
}
