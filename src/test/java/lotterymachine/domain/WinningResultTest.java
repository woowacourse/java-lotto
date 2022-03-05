package lotterymachine.domain;

import lotterymachine.domain.vo.Money;
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
                .mapToObj(LotteryNumber::from)
                .collect(Collectors.toList());
        List<LotteryTicket> tickets = List.of(new LotteryTicket(lotteryNumbers));
        LotteryTickets lotteryTickets = new LotteryTickets(tickets);

        LotteryNumber bonusNumber = LotteryNumber.from(7);
        WinningLottery winningLottery = new WinningLottery(new LotteryTicket(lotteryNumbers), bonusNumber);

        WinningResult winningResult = WinningResult.create(lotteryTickets, winningLottery);
        Map<WinningLotteryRank, Integer> value = winningResult.getResult();
        assertThat(value.get(WinningLotteryRank.SIX)).isEqualTo(1);
    }

    @Test
    @DisplayName("당첨 결과에 대한 투자 수익률을 조회한다.")
    void getTotalProfitRate() {
        Money money = new Money(14000);
        List<LotteryNumber> lotteryNumbers = IntStream.rangeClosed(1, 6)
                .mapToObj(LotteryNumber::from)
                .collect(Collectors.toList());
        List<LotteryTicket> tickets = List.of(new LotteryTicket(lotteryNumbers));
        LotteryTickets lotteryTickets = new LotteryTickets(tickets);

        LotteryNumber bonusNumber = LotteryNumber.from(7);
        WinningLottery winningLottery = new WinningLottery(new LotteryTicket(lotteryNumbers), bonusNumber);

        WinningResult winningResult = WinningResult.create(lotteryTickets, winningLottery);
        double totalProfitRate = winningResult.getTotalProfitRate(money.getValue());
        assertThat(totalProfitRate).isEqualTo(142857.14);
    }
}