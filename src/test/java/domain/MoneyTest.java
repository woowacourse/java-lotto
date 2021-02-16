package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @DisplayName("Money를 생성하는 기능")
    @Test
    void generate() {
        //given
        int moneyValue = 1000;

        //when
        Money money = new Money(moneyValue);

        //then
        assertThat(money).isNotNull();
    }
}