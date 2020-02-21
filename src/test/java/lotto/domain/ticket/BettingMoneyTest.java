package lotto.domain.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingMoneyTest {

    @DisplayName("최소 구매 금액 1000원보다 적은 금액을 입력한 경우 Exception 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999})
    void test1(int bettingMoney) {
        assertThatThrownBy(() -> new BettingMoney(bettingMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%d는 최소 구매 금액보다 작습니다.", bettingMoney);
    }

    @DisplayName("입력받은 금액을 티켓을 살 수 있는양으로 반환")
    @ParameterizedTest
    @CsvSource(value = {"1000,1", "1500,1", "2000,2"})
    void test2(int inputBettingMoney, int result) {
        BettingMoney bettingMoney = new BettingMoney(inputBettingMoney);

        assertThat(bettingMoney.getTicketCount()).isEqualTo(result);
    }
}