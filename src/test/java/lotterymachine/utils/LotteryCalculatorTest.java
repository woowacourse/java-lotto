package lotterymachine.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotterymachine.dto.LotteryResultDto;
import lotterymachine.model.LotteryTicket;
import lotterymachine.model.LotteryTickets;
import lotterymachine.model.WinningType;
import lotterymachine.vo.Ball;
import lotterymachine.vo.Count;
import lotterymachine.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6:2000000000", "1,2,3,4,5,7:30000000", "1,2,3,4,5,8:1500000", "1,2,3,4,8,9:50000",
            "1,2,3,7,8,9:5000", "1,2,7,8,9,10:0"}, delimiter = ':')
    @DisplayName("당첨 총 금액을 계산한다.")
    void getWinningAmount(String winningNumbers, int amount) {
        LotteryTicket lotteryTicket = new LotteryTicket(Ball.getBalls(
                Arrays.stream(winningNumbers.split(",")).map(Integer::parseInt).collect(Collectors.toList())));
        LotteryTickets lotteryTickets = new LotteryTickets(List.of(lotteryTicket));
        LotteryTicket winningTicket = new LotteryTicket(Ball.getBalls(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Ball bonus = Ball.from(7);
        Map<WinningType, Count> ticketsResult = lotteryTickets.getLotteriesResult(winningTicket, bonus);
        List<LotteryResultDto> lotteryResults = LotteryResultDto.createLotteryResults(ticketsResult);
        Money winningAmount = LotteryCalculator.getWinningAmount(lotteryResults);

        Money expected = Money.from(amount);
        assertThat(winningAmount).isEqualTo(expected);
    }

    @Test
    @DisplayName("투입된 금액을 통해 구매할 수 있는 로또 티켓 개수를 구한다.")
    void divideByTicketPrice() {
        Money inputMoney = Money.fromInputAmount(14000);
        Count numberOfTickets = LotteryCalculator.divideByLotteryPrice(inputMoney);
        Count expected = Count.from(14);
        assertThat(numberOfTickets).isEqualTo(expected);
    }
}
