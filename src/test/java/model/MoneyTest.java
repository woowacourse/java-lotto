package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("로또를 구매하는 돈이 저장되는지 확인한다.")
    void generateMoney_Test() {
        final Money money = new Money(14000);

        assertThat(money.getMoney()).isEqualTo(14000);
    }
}