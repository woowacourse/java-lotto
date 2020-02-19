package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningResultTest {
//    FIRST_PLACE{
//
//    },TWO_PLACE,THIRD_PLACE,FOURTH_PLACE,FIFTH_PLACE;

    @Test
    void 로또번호와_당첨결과_비교() {
        int[] value = {1,2,3,4,5,6};
        int[] vlaue2 = {3,4,5,6,7,8};
        List<LottoBall> winningBallValues = Arrays.stream(value).mapToObj(LottoBall::new).collect(Collectors.toList());
        List<LottoBall> winningBallValues1 = Arrays.stream(vlaue2).mapToObj(LottoBall::new).collect(Collectors.toList());
        WinningBalls winningBalls = new WinningBalls(winningBallValues,7);

        LottoTicket lottoTicket = new LottoTicket(winningBallValues1);

        Assertions.assertThat(winningBalls.hitLottoBalls(lottoTicket)).isEqualTo(6);
    }
}
