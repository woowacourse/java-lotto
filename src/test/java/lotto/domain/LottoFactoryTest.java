package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoFactoryTest {

	@ParameterizedTest
	@ValueSource(ints = {1, 12, 23, 4, 5, 6})
	void makeLotto(int ball) {
		LottoFactory lottoFactory = () -> {
			List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(12), Ball.of(23), Ball.of(4), Ball.of(5), Ball.of(6));
			return new Lotto(balls);
		};
		assertThat(lottoFactory.create().contains(Ball.of(ball))).isTrue();
	}

}
