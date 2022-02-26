package lotto.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.ticket.generator.CustomTicketGenerator;
import lotto.exception.LottoException;
import lotto.exception.money.MoneyExceptionStatus;

class LottoServiceTest {

    private final CustomTicketGenerator customTicketGenerator = new CustomTicketGenerator();
    private final LottoService lottoService = new LottoService(customTicketGenerator);

    private void saveMoneyExceptionTest(final int money, final MoneyExceptionStatus exceptionStatus) {
        assertThatThrownBy(() -> lottoService.saveMoney(money))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining(exceptionStatus.getMessage());
    }

    @DisplayName("구입금액은 0원이 될 수 없습니다.")
    @ParameterizedTest
    @ValueSource(ints = {0})
    void saveMoneyZeroExceptionTest(final int money) {
        saveMoneyExceptionTest(money, MoneyExceptionStatus.MONEY_IS_ZERO);
    }

    @DisplayName("구입 금액은 1000으로 나누어 떨어져야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {10, 1100, 200001})
    void saveMoneyNotDivisibleExceptionTest(final int money) {
        saveMoneyExceptionTest(money, MoneyExceptionStatus.MONEY_IS_NOT_DIVISIBLE);
    }

    @DisplayName("구입 금액 저장 기능 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1000, 3000, 15000})
    void saveMoneyTest(final int money) {
        assertDoesNotThrow(() -> lottoService.saveMoney(money));
    }

}
