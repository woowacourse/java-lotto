package lotterymachine.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryTicketsTest {

    @Test
    @DisplayName("6개의 숫자 리스트를 입력 받아 로또 티켓을 추가한다.")
    void add() {
        LotteryTickets lotteryTickets = new LotteryTickets();
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        lotteryTickets.add(numbers);

        assertThat(lotteryTickets.getLotteryTickets().size()).isEqualTo(1);
    }

//    @Test
//    @DisplayName("당첨번호와 비교하여 로또 결과를 조회한다.")
//    void findResult() {
//        LotteryTicket lotteryTicket = new LotteryTicket(Arrays.asList(7, 8, 9, 10, 11, 12));
//        LotteryTickets lotteryTickets = new LotteryTickets(List.of(lotteryTicket));
//        List<Integer> winningNumbers = Arrays.asList(7, 8, 9, 13, 20, 21);
//        int bonusNumber = 30;
//        Map<WinningLottery, Integer> result = lotteryTickets.getLotteriesResult(winningNumbers, bonusNumber);
//        assertThat(result.get(WinningLottery.THREE)).isEqualTo(1);
//    }
}
