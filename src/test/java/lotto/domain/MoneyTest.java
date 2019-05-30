package lotto.domain;

import lotto.exception.IllegalNumberBoundException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    void create() {
        assertThat(new Money(5000)).isEqualTo(new Money(5000));
    }

    @Test
    void 올바른_전체_게임횟수_리턴하는지_테스트() {
        Money money = new Money(5000);
        assertThat(money.getTicketCount()).isEqualTo(5);
    }

    @Test
    void 구입금액이_1000원_미만일때() {
        assertThrows(IllegalNumberBoundException.class, () -> new Money(500));
    }
}
