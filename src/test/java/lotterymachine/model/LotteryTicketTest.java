package lotterymachine.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotterymachine.vo.Ball;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LotteryTicketTest {

    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5,6:0", "1,2,3,4,7,8:2", "1,2,3,7,8,9:3"}, delimiter = ':')
    @DisplayName("당첨번호와 일치하는 로또 숫자의 개수를 반환한다.")
    void countMatchingNumbers(String winningNumbers, int expected) {
        LotteryTicket lotteryTicket = new LotteryTicket(Ball.createBalls(Arrays.asList(7, 8, 9, 10, 11, 12)));
        List<Ball> winningBalls = Arrays.stream(winningNumbers.split(","))
                .map(Integer::parseInt)
                .map(Ball::from)
                .collect(Collectors.toList());
        assertThat(lotteryTicket.countMatchingBalls(winningBalls)).isEqualTo(expected);
    }


    @ParameterizedTest
    @CsvSource(value = {"1:false", "30:true"}, delimiter = ':')
    @DisplayName("보너스 볼 보유 여부를 확인한다.")
    void matchBonusNumber(int bonusNumber, boolean expected) {
        LotteryTicket lotteryTicket = new LotteryTicket(Ball.createBalls(Arrays.asList(7, 8, 9, 10, 11, 30)));
        Ball bonus = Ball.from(bonusNumber);
        assertThat(lotteryTicket.contains(bonus)).isEqualTo(expected);
    }
}
