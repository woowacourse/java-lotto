package lotto.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;

class MoneyTest {

    @DisplayName("구입 금액은 양수여야 합니다.")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}")
    @ValueSource(ints = {-1000, -5, 0})
    void moneyNotPositiveExceptionTest(final int money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(LottoExceptionStatus.MONEY_MUST_BE_POSITIVE.getMessage());
    }

    @DisplayName("구입 금액은 1000으로 나누어 떨어져야 한다.")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}")
    @ValueSource(ints = {100, 1100, 200001})
    void moneyIsDivisibleExceptionTest(final int money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(LottoExceptionStatus.MONEY_MUST_BE_DIVISIBLE.getMessage());
    }

    @DisplayName("금액 객체는 생성 당시 주어진 값을 지니고 있어야 합니다.")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}")
    @ValueSource(ints = {1000, 12000, 1300000})
    void initTest(final int expectedMoney) {
        final Money money = new Money(expectedMoney);
        assertThat(money.getMoney()).isEqualTo(expectedMoney);
    }

}
