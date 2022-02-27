package lotterymachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryTicketsTest {

    @Test
    @DisplayName("6개의 숫자 리스트를 입력 받아 로또 티켓을 추가한다.")
    void add() {
        LotteryTickets lotteryTickets = new LotteryTickets(1);
        List<LotteryNumber> input = IntStream.range(7, 13)
                .mapToObj(LotteryNumber::new)
                .collect(Collectors.toList());

        lotteryTickets.add(input);

        assertThat(lotteryTickets.getLotteryTickets().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("티켓 개수보다 초과로 add할 시, 추가되지 않는다.")
    void overCount() {
        LotteryTickets lotteryTickets = new LotteryTickets(0);
        List<LotteryNumber> input = IntStream.range(7, 13)
                .mapToObj(LotteryNumber::new)
                .collect(Collectors.toList());

        lotteryTickets.add(input);

        assertThat(lotteryTickets.getLotteryTickets().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("당첨번호와 비교하여 로또 1등인 결과를 조회한다.")
    void findResult() {
        LotteryTickets lotteryTickets = new LotteryTickets(2);
        List<LotteryNumber> input = IntStream.range(7, 13)
                .mapToObj(LotteryNumber::new)
                .collect(Collectors.toList());
        lotteryTickets.add(input);
        int bonusNumber = 30;
        WinningLotteryNumbers winningLotteryNumbers = new WinningLotteryNumbers(input, new LotteryNumber(bonusNumber));
        Map<WinningLottery, Integer> result = lotteryTickets.getLotteriesResult(winningLotteryNumbers);
        assertThat(result.get(WinningLottery.SIX)).isEqualTo(1);
    }
}
