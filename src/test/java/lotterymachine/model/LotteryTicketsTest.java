package lotterymachine.model;

import lotterymachine.vo.Ball;
import lotterymachine.vo.Count;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryTicketsTest {

    @Test
    @DisplayName("당첨번호와 비교하여 로또 결과를 조회한다.")
    void findResult() {
        LotteryTicket lotteryTicket = new LotteryTicket(Ball.createBalls(Arrays.asList(7, 8, 9, 10, 11, 12)));
        LotteryTickets lotteryTickets = new LotteryTickets(List.of(lotteryTicket));
        List<Ball> winningBalls = Ball.createBalls(Arrays.asList(7, 8, 9, 13, 20, 21));
        Ball bonus = Ball.from(30);
        Map<WinningLottery, Count> result = lotteryTickets.getLotteriesResult(winningBalls, bonus);
        assertThat(result.get(WinningLottery.THREE).getNumber()).isEqualTo(1);
    }
}
