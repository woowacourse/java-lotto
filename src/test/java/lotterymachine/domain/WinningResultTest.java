package lotterymachine.domain;

import lotterymachine.LotteryPurchase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningResultTest {
    @Test
    @DisplayName("당첨 번호와 로또 티켓을 입력 받아 결과를 저장한다.")
    void getResult() {
        List<LotteryNumber> lotteryNumbers = IntStream.rangeClosed(1, 6)
                .mapToObj(LotteryNumber::of)
                .collect(Collectors.toList());
        List<LotteryTicket> tickets = List.of(new LotteryTicket(lotteryNumbers));
        LotteryTickets lotteryTickets = new LotteryTickets(tickets);

        LotteryNumber bonusNumber = LotteryNumber.of(7);
        WinningLottery winningLottery = new WinningLottery(lotteryNumbers, bonusNumber);

        WinningResult winningResult = new WinningResult(lotteryTickets, winningLottery);
        Map<WinningLotteryRank, Integer> result = winningResult.getResult();
        assertThat(result.get(WinningLotteryRank.SIX)).isEqualTo(1);
    }

    @Test
    @DisplayName("당첨 결과에 대한 투자 수익률을 조회한다.")
    void getTotalProfitRate() {
        LotteryPurchase lotteryPurchase = new LotteryPurchase(14000, 3);
        List<LotteryNumber> lotteryNumbers = IntStream.rangeClosed(1, 6)
                .mapToObj(LotteryNumber::of)
                .collect(Collectors.toList());
        List<LotteryTicket> tickets = List.of(new LotteryTicket(lotteryNumbers));
        LotteryTickets lotteryTickets = new LotteryTickets(tickets);

        LotteryNumber bonusNumber = LotteryNumber.of(7);
        WinningLottery winningLottery = new WinningLottery(lotteryNumbers, bonusNumber);

        WinningResult winningResult = new WinningResult(lotteryTickets, winningLottery);
        double totalProfitRate = winningResult.getTotalProfitRate(lotteryPurchase);
        assertThat(totalProfitRate).isEqualTo(142857.14);
    }
}