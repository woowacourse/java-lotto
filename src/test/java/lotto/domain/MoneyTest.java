package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {
    @Test
    void 돈을_소유하는_객체_생성() {
        Money money = new Money(1000);
        assertThat(money).isEqualTo(new Money(1000));
    }

    @Test
    void 최소금액_이하로_입력하는_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Money(900);
        });
    }

    @Test
    void 거스름_돈이_있는_경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Money(1902);
        });
    }

    @Test
    void 로또_구입_개수_반환() {
        Money money = new Money(2000);
        assertThat(money.purchaseCount()).isEqualTo(2);
    }
}
