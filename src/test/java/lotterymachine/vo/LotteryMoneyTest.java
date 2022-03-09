package lotterymachine.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LotteryMoneyTest {
    @Test
    @DisplayName("당첨 금액을 투입된 금액으로 나눈다.")
    void divide() {
        LotteryMoney inputMoney = LotteryMoney.fromInputAmount(14000);
        LotteryMoney winningMoney = LotteryMoney.from(5000);
        double expected = 5000.0 / 14000;
        assertThat(winningMoney.divide(inputMoney)).isEqualTo(expected);
    }

    @Test
    @DisplayName("투입된 금액을 통해 구매할 수 있는 로또 티켓 개수를 구한다.")
    void divideByTicketPrice() {
        LotteryMoney inputMoney = LotteryMoney.fromInputAmount(14000);
        Count numberOfTickets = inputMoney.divideByLotteryPrice();
        Count expected = Count.from(14);
        assertThat(numberOfTickets).isEqualTo(expected);
    }
}
