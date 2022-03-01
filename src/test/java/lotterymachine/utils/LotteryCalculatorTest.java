package lotterymachine.utils;

import lotterymachine.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LotteryCalculatorTest {

    @Test
    @DisplayName("당첨금액, 구입금액을 입력하여 수익률을 계산한다.")
    void calculateProfitRate() {
        Money amount = Money.fromInputAmount(15000);
        Money winningLotteryAmount = Money.from(1000);
        double result = LotteryCalculator.calculateProfitRate(winningLotteryAmount, amount);
        assertThat(result).isEqualTo(0.06);
    }

    @Test
    @DisplayName("로또 개수로 로또 구입 비용을 계산한다.")
    void getTotalTicketAmount() {
        int numberOfTickets = 14;
        Money totalTicketAmount = LotteryCalculator.getTotalTicketAmount(numberOfTickets);
        Money expected = Money.from(14000);
        assertThat(totalTicketAmount).isEqualTo(expected);
    }
}
