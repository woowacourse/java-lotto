package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MoneyTest {
    @Test
    void 구매가능한_로또_개수_판별하는_테스트() {
        Money money = new Money(14000);
        assertThat(money.countOfLotto()).isEqualTo(14);
    }

    @Test
    void 한장도_구매하지_못하는_경우_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new Money(999);
        }).withMessage("로또 1장의 가격은 1,000원 입니다.");
    }
}
