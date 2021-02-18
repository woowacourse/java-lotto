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
        Money money = new Money(1_000L);

        //when
        long number = money.divide(1_000L);

        //then
        assertThat(number).isEqualTo(1);
    }

    @DisplayName("구입금액을 0이나 음수로 나눈 값을 반환하는 기능")
    @ParameterizedTest
    @ValueSource(longs = {
            0, -1, -2, -3
    })
    void divide(long value) {
        //given
        Money money = new Money(1_000);

        //when //then
        assertThatThrownBy(() -> money.divide(value))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("돈을 더하는 기능을 테스트한다")
    @Test
    void add() {
        //given
        Money firstMoney = new Money(1L);
        Money secondMoney = new Money(2L);

        //when
        Money sumMoney = firstMoney.add(secondMoney);

        //then
        assertThat(sumMoney).isEqualTo(new Money(3L));
    }

    @DisplayName("돈을 곱하는 기능")
    @Test
    void multiply() {
        //given
        Money money = new Money(2L);
        long multipliedNumber = 3L;

        //when
        Money result = money.multiply(multipliedNumber);

        //then
        assertThat(result).isEqualTo(new Money(6L));
    }
}