package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LottosFactoryTest {

	@Test
	void makeLottos() {
		LottoFactory lottoFactory = () -> {
			List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(12), Ball.of(23), Ball.of(4), Ball.of(5), Ball.of(6));
			return new Lotto(balls);
		};
		LottosFactory lottosFactory = new LottosFactory(lottoFactory);

		Lottos lottos = lottosFactory.createLottosByCount(new LottoCount(5));
		assertThat(lottos.isRightSize(5)).isTrue();
	}
}
