package lotto.domain.credit;

import static org.assertj.core.api.Assertions.*;

import lotto.exception.LottoException;
import lotto.exception.credit.CreditMoneyExceptionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CreditTest {

    @DisplayName("구입 금액은 1000으로 나누어 떨어져야 한다.")
    @Test
    void moneyIsDivisibleExceptionTest() {
        final int money = 1410;
        assertThatThrownBy(() -> new Credit(money))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(CreditMoneyExceptionStatus.MONEY_IS_NOT_DIVISIBLE.getMessage());

    }

}
