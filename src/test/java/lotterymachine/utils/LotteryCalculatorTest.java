package lotterymachine.utils;

import lotterymachine.vo.Count;
import lotterymachine.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LotteryCalculatorTest {
    @Test
    @DisplayName("투입된 금액을 통해 구매할 수 있는 로또 티켓 개수를 구한다.")
    void divideByTicketPrice() {
        Money inputMoney = Money.fromInputAmount(14000);
        Count numberOfTickets = LotteryCalculator.divideByLotteryPrice(inputMoney);
        Count expected = Count.from(14);
        assertThat(numberOfTickets).isEqualTo(expected);
    }
}
