package lotto.domain.credit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.exception.LottoException;
import lotto.exception.credit.CreditMoneyExceptionStatus;

class CreditTest {

    @DisplayName("구입 금액은 1000으로 나누어 떨어져야 한다.")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}")
    @ValueSource(ints = {100, 1100, 200001})
    void moneyIsDivisibleExceptionTest(final int creditMoney) {
        assertThatThrownBy(() -> new Credit(creditMoney))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(CreditMoneyExceptionStatus.MONEY_IS_NOT_DIVISIBLE.getMessage());
    }

    @DisplayName("구입 금액 생성자 기능 테스트")
    @ParameterizedTest(name = "[{index}] 구입 금액 : {0}")
    @ValueSource(ints = {1000, 12000, 1300000})
    void initTest(final int creditMoney) {
        final Credit credit = new Credit(creditMoney);
        assertThat(credit.getMoney()).isEqualTo(creditMoney);
    }

}
