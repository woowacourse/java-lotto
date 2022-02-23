package lotterymachine.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryTicketsTest {

    @Test
    @DisplayName("당첨번호와 3개가 일치하는 로또 티켓 개수를 구한다.")
    void findThree() {
        LotteryTicket lotteryTicket = new LotteryTicket(Arrays.asList(7, 8, 9, 10, 11, 12));
        LotteryTickets lotteryTickets = new LotteryTickets(List.of(lotteryTicket));
        List<Integer> winningNumbers = Arrays.asList(7, 8, 9, 13, 20, 21);
        int count = lotteryTickets.countMatchingNumberOfThree(winningNumbers);
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("당첨번호와 4개가 일치하는 로또 티켓 개수를 구한다.")
    void findFour() {
        LotteryTicket lotteryTicket = new LotteryTicket(Arrays.asList(7, 8, 9, 10, 11, 12));
        LotteryTickets lotteryTickets = new LotteryTickets(List.of(lotteryTicket));
        List<Integer> winningNumbers = Arrays.asList(7, 8, 9, 10, 20, 21);
        int count = lotteryTickets.countMatchingNumberOfFour(winningNumbers, 4);
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("당첨번호와 3개가 일치하는 로또 티켓 개수를 구한다.")
    void findResult() {
        LotteryTicket lotteryTicket = new LotteryTicket(Arrays.asList(7, 8, 9, 10, 11, 12));
        LotteryTickets lotteryTickets = new LotteryTickets(List.of(lotteryTicket));
        List<Integer> winningNumbers = Arrays.asList(7, 8, 9, 13, 20, 21);
        int bonusNumber = 30;
        Map<WinningLottery, Integer> result = lotteryTickets.getWinningLotteries(winningNumbers, bonusNumber);
        assertThat(result.get(WinningLottery.THREE)).isEqualTo(1);
    }
}
