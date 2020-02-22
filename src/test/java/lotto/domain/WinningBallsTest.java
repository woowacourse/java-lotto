package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningBallsTest {

    @Test
    @DisplayName("자동 생성된 로또볼과 당첨 로또볼과 비교하는 테스트")
    void compare_lotto_ball_and_winning_ball() {
        int[] value = {1, 2, 3, 4, 5, 6};
        Set<LottoBall> lottoBalls = Arrays.stream(value).mapToObj(LottoBall::new).collect(Collectors.toSet());
        WinningBalls winningBalls = new WinningBalls(new ArrayList<>(lottoBalls), LottoBallFactory.findByLottoBall(7));

        LottoTicket lottoTicket = new LottoTicket(lottoBalls);

        Assertions.assertThat(winningBalls.hitLottoBalls(lottoTicket)).isEqualTo(6);
    }

    @Test
    @DisplayName("보너스번호가 있는경우 테스트")
    void present_bonus_balls() {
        int bonusBall = 10;
        int[] winningBallInputs = {3, 4, 5, 6, 7, 8};
        int[] lottoTicketNumbers = {3, 4, 5, 6, 7, 10};

        List<LottoBall> winningBallValues = Arrays.stream(winningBallInputs)
                .mapToObj(LottoBallFactory::findByLottoBall)
                .collect(Collectors.toList());
        Set<LottoBall> lottoTicket = Arrays.stream(lottoTicketNumbers)
                .mapToObj(LottoBallFactory::findByLottoBall)
                .collect(Collectors.toSet());

        WinningBalls winningBalls = new WinningBalls(winningBallValues, LottoBallFactory.findByLottoBall(bonusBall));

        Assertions.assertThat(winningBalls.hitBonusBall(new LottoTicket(lottoTicket))).isTrue();
    }
}
