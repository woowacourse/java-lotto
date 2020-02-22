package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @DisplayName("당첨 번호와 보너스볼 중복시 예외")
    @Test
    void duplicateBonusBallAndLotto() {
        Set<Ball> balls = new HashSet<>(
            Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(6)));
        Lotto lotto = new Lotto(balls);
        Ball ball = Ball.of(6);

        assertThatThrownBy(() -> new WinningLotto(lotto, ball))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 당첨 내역 랭크 반환")
    @Test
    void getResult() {
        Set<Ball> balls = new HashSet<>(
            Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(7)));
        Lotto lotto = new Lotto(balls);
        Ball ball = Ball.of(40);
        WinningLotto winningLotto = new WinningLotto(lotto, ball);

        LottoFactory lottoFactory = () -> new Lotto(balls);

        LottosFactory lottosFactory = new LottosFactory(lottoFactory);
        Lottos lottos = lottosFactory.createLottosByCount(new LottoCount(2));

        TotalResult totalResult = winningLotto.getResult(lottos);
        assertThat(totalResult.getProfitRate()).isEqualTo(200000000);
    }
}
