package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("Money 값으로 음수가 입력되는 경우")
    @Test
    void generateMoneyWithNegativeValue() {
        //given
        int moneyValue = -1;

        //when //then
        assertThatThrownBy(() -> {
            new Money(moneyValue);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}