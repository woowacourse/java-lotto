package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    void 돈_생성_테스트() {
        Money money = new Money(10000);
        assertThat(money).isInstanceOf(Money.class);
    }

    @Test
    void 돈_생성_테스트_음수() {
        assertThatThrownBy(() ->
                new Money(-2))
                .isInstanceOf(RuntimeException.class);
    }
}
