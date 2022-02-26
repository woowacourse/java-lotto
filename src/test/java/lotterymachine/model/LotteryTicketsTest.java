package lotterymachine.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryTicketsTest {

    @Test
    @DisplayName("6개의 숫자 리스트를 입력 받아 로또 티켓을 추가한다.")
    void add() {
        LotteryTickets lotteryTickets = new LotteryTickets(1);
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        lotteryTickets.add(numbers);

        assertThat(lotteryTickets.getLotteryTickets().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("티켓 개수보다 초과로 add할 시, 추가되지 않는다.")
    void overCount() {
        LotteryTickets lotteryTickets = new LotteryTickets(0);
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        lotteryTickets.add(numbers);

        assertThat(lotteryTickets.getLotteryTickets().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("당첨번호와 비교하여 로또 1등인 결과를 조회한다.")
    void findResult() {
        LotteryTickets lotteryTickets = new LotteryTickets(2);
        List<Integer> numbers1 = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> numbers2 = List.of(1, 2, 3, 7, 8, 9);
        lotteryTickets.add(numbers1);
        lotteryTickets.add(numbers2);
        int bonusNumber = 30;
        WinningLotteryNumbers winningLotteryNumbers = new WinningLotteryNumbers(numbers1, bonusNumber);
        Map<WinningLottery, Integer> result = lotteryTickets.getLotteriesResult(winningLotteryNumbers);
        for (WinningLottery winningLottery : result.keySet()) {
            System.out.println(result.get(winningLottery));
        }
        assertThat(lotteryTickets.getLotteryTickets().size()).isEqualTo(2);
        assertThat(result.get(WinningLottery.SIX)).isEqualTo(1);
    }
}
