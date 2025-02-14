package lotto.model.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -1000})
    void 생성_시_금액이_음수이면_예외_발생(int value) {

        assertThrows(IllegalArgumentException.class, () -> Money.from(value));
    }

    @ParameterizedTest
    @CsvSource(value = {"10,15", "10,11", "10,12"}, delimiter = ',')
    void 나누기_시_나누는_수가_금액보다_크면_예외_발생(int value, int divisorValue) {
        Money money = Money.from(value);
        Money divisor = Money.from(divisorValue);

        assertThrows(IllegalArgumentException.class, () -> money.divide(divisor));
    }

    @ParameterizedTest
    @CsvSource(value = {"10,21", "10,32", "10,11"}, delimiter = ',')
    void 나누기_시_나누어_떨어지지_않으면_예외_발생(int value, int divisorValue) {
        Money money = Money.from(value);
        Money divisor = Money.from(divisorValue);

        assertThrows(IllegalArgumentException.class, () -> money.divide(divisor));
    }

    @ParameterizedTest
    @CsvSource(value = {"10,10,1", "20,10,2", "50,10,5"}, delimiter = ',')
    void 나누기_시_나누어_떨어지면_나눈_값을_반환(int value, int divisorValue, int expected) {
        Money money = Money.from(value);
        Money divisor = Money.from(divisorValue);

        int actual = money.divide(divisor);

        assertThat(actual).isEqualTo(expected);
    }
}