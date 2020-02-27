package lotto.domain;

import lotto.exception.InvalidMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @ParameterizedTest
    @DisplayName("로또 구매 매수 계산")
    @CsvSource(value = {"1000,1", "2000,2", "3333,3"})
    void purchaseTest(int input, int result) {
        Money money = new Money(input);
        assertThat(money.calculateLottoTicketCount()).isEqualTo(result);
    }

    @DisplayName("금액 1000원 미만 예외처리 테스트")
    @Test
    void purchaseExceptionTest() {
        assertThatThrownBy(() -> Money.validateMoney(999))
                .isInstanceOf(InvalidMoneyException.class);
    }
}

