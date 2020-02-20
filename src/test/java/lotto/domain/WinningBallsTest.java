package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningBallsTest {

    @Test
    void 로또번호와_당첨결과_비교() {
        int[] value = {1, 2, 3, 4, 5, 6};
        List<LottoBall> winningBallValues = Arrays.stream(value).mapToObj(LottoBall::new).collect(Collectors.toList());
        WinningBalls winningBalls = new WinningBalls(winningBallValues, 7);

        LottoTicket lottoTicket = new LottoTicket(winningBallValues);

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
        List<LottoBall> lottoTicket = Arrays.stream(lottoTicketNumbers)
                .mapToObj(LottoBallFactory::findByLottoBall)
                .collect(Collectors.toList());

        WinningBalls winningBalls = new WinningBalls(winningBallValues, bonusBall);

        Assertions.assertThat(winningBalls.hitBonusBall(new LottoTicket(lottoTicket))).isTrue();
    }
}
