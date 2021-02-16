package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("구입금액을 특정 값으로 나눈 값을 반환하는 기능")
    @Test
    void divide() {
        //given
        Money money = new Money(1_000);

        //when
        int number = money.divide(1_000);

        //then
        assertThat(number).isEqualTo(1);
    }

    @DisplayName("구입금액을 0이나 음수로 나눈 값을 반환하는 기능")
    @ParameterizedTest
    @ValueSource(ints = {
            0, -1, -2, -3
    })
    void divide(int value) {
        //given
        Money money = new Money(1_000);

        //when //then
        assertThatThrownBy(() -> money.divide(value))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}