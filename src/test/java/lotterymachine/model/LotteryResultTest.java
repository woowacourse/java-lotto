package lotterymachine.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotterymachine.vo.Ball;
import lotterymachine.vo.Count;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LotteryResultTest {

    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6:2000000.0", "1,2,3,7,8,9:5.0", "1,2,7,8,9,10:0.0"}, delimiter = ':')
    @DisplayName("당첨금액, 구입금액을 입력하여 수익률을 계산한다.")
    void getProfitRate(String winningNumbers, double expected) {
        LotteryTicket lotteryTicket = new LotteryTicket(Ball.getBalls(
                Arrays.stream(winningNumbers.split(",")).map(Integer::parseInt).collect(Collectors.toList())));
        LotteryTickets lotteryTickets = new LotteryTickets(List.of(lotteryTicket));
        WinningLottery winningLottery = new WinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        LotteryResult lotteryResult = new LotteryResult(lotteryTickets, winningLottery);
        Map<WinningType, Count> ticketResult = lotteryResult.compute();

        double profitRate = lotteryResult.getProfitRate(ticketResult);
        assertThat(profitRate).isEqualTo(expected);
    }
}
