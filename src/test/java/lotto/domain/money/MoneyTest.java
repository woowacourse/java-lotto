package lotto.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;

class MoneyTest {

    private static final int MONEY_UNIT = 1000;

    private void moneyExceptionTest(final int money, final LottoExceptionStatus exceptionStatus) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(exceptionStatus.getMessage());
    }

    @DisplayName("구입 금액은 양수여야 합니다.")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}")
    @ValueSource(ints = {-1000, -5, 0})
    void moneyNotPositiveExceptionTest(final int money) {
        moneyExceptionTest(money, LottoExceptionStatus.MONEY_MUST_BE_POSITIVE);
    }

    @DisplayName("구입 금액은 1000으로 나누어 떨어져야 한다.")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}")
    @ValueSource(ints = {100, 1100, 200001})
    void moneyIsDivisibleExceptionTest(final int money) {
        moneyExceptionTest(money, LottoExceptionStatus.MONEY_MUST_BE_DIVISIBLE);
    }

    @DisplayName("금액 객체는 1000으로 나눠진 몫을 반환할 수 있어야 합니다.")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}")
    @ValueSource(ints = {1000, 12000, 1300000})
    void getQuotientTest(final int money) {
        final int actualQuotient = (new Money(money)).getQuotient();
        final int expectedQuotient = money / MONEY_UNIT;
        assertThat(actualQuotient).isEqualTo(expectedQuotient);
    }

    @DisplayName("주어진 금액을 스스로의 금액으로 나눌 수 있어야 합니다.")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}")
    @CsvSource(value = {"1000, 5000", "1000, 12000", "650000, 1300000"}, delimiter = ',')
    void divideTest(final int selfMoney, final int targetMoney) {
        final Money money = new Money(selfMoney);
        final double actualDividingResult = money.divide(targetMoney);
        final double expectedDividingResult = (double) targetMoney / selfMoney;
        assertThat(actualDividingResult).isEqualTo(expectedDividingResult);
    }


}
