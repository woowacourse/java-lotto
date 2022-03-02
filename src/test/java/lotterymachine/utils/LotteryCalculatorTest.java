package lotterymachine.utils;

import lotterymachine.domain.LotteryNumber;
import lotterymachine.domain.LotteryTicket;
import lotterymachine.domain.LotteryTickets;
import lotterymachine.domain.WinningLottery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryCalculatorTest {

    @Test
    @DisplayName("구입 금액에서 최대한으로 구매할 수 있는 로또 개수 계산한다.")
    void calculateMaximumLottery() {
        int input = 15300;
        int lottery = LotteryCalculator.divideByLotteryPrice(input);
        assertThat(lottery).isEqualTo(15);
    }

    @Test
    @DisplayName("당첨금액, 구입금액을 입력하여 수익률을 계산한다.")
    void calculateProfitRate() {
        int amount = 15000;
        int winningLotteryAmount = 1000;
        double result = LotteryCalculator.calculateProfitRate(winningLotteryAmount, amount);
        assertThat(result).isEqualTo(0.06);
    }

    @Test
    @DisplayName("로또 결과를 입력 받아, 수익을 계산하여 반환한다.")
    void totalProfit() {
        List<LotteryNumber> input = IntStream.range(1, 7)
                .mapToObj(LotteryNumber::new)
                .collect(Collectors.toList());
        List<LotteryTicket> tickets = List.of(new LotteryTicket(input));
        LotteryTickets lotteryTickets = new LotteryTickets(tickets);


        List<LotteryNumber> input2 = IntStream.range(4, 10)
                .mapToObj(LotteryNumber::new)
                .collect(Collectors.toList());
        WinningLottery winningLottery = new WinningLottery(input2, new LotteryNumber(12));
        lotteryTickets.getLotteriesResult(winningLottery);
        int result = LotteryCalculator.totalProfit(lotteryTickets.getLotteriesResult(winningLottery));
        assertThat(result).isEqualTo(5000);
    }
}
