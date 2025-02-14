package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MoneyTest {

    @Test
    void amount가_음수인_경우_예외를_반환한다() {
        assertThatThrownBy(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("돈은 음수가 될 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "1000, 900, true",
            "900, 900, true",
            "800, 900, false",
    })
    void 돈의_양이_크거나_같은지_알_수_있다(int amount1, int amount2, boolean expected) {
        //given
        Money money1 = new Money(amount1);
        Money money2 = new Money(amount2);

        //when
        boolean result = money1.isGreaterOrEqualThan(money2);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 돈의_양을_뺄_수_있다() {
        //given
        Money money1 = new Money(1000);
        Money money2 = new Money(900);

        //when
        Money resultMoney = money1.minus(money2);

        //then
        assertThat(resultMoney).isEqualTo(new Money(100));
    }

    @Test
    void 돈의_양을_더할_수_있다() {
        //given
        Money money1 = new Money(1000);
        Money money2 = new Money(900);

        //when
        Money resultMoney = money1.sum(money2);

        //then
        assertThat(resultMoney).isEqualTo(new Money(1900));
    }

    @Test
    void 돈의_양을_곱할_수_있다() {
        //given
        Money money = new Money(1000);

        //when
        Money resultMoney = money.multiply(3);

        //then
        assertThat(resultMoney).isEqualTo(new Money(3000));
    }

    @Test
    void 돈의_양을_나눌_수_있다() {
        //given
        Money money1 = new Money(1000);
        Money money2 = new Money(500);

        //when
        double result = money1.divide(money2);

        //then
        assertThat(result).isEqualTo(2.00);
    }

}