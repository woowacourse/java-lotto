package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

	@DisplayName("당첨 번호와 보너스볼 중복시 예외")
	@Test
	void duplicateBonusBallAndLotto() {
		List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(6));
		Lotto lotto = new Lotto(balls);
		Ball ball = Ball.of(6);

		assertThatThrownBy(() -> new WinningLotto(lotto, ball))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("로또 당첨 내역 랭크 반환")
	@Test
	void getResult() {
		List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3), Ball.of(4), Ball.of(5), Ball.of(7));
		Lotto lotto = new Lotto(balls);
		Ball ball = Ball.of(40);
		WinningLotto winningLotto = new WinningLotto(lotto, ball);

		Lottos lottos = LottosFactory.createLottosByCount(2);

		TotalResult totalResult = winningLotto.getResult(lottos);
		assertThat(totalResult.getProfitRate()).isEqualTo(150000);
	}
}
