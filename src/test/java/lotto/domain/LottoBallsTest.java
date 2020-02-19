package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LottoBallsTest {
	@Test
	void 로또_랜덤번호_6개_뽑기() {
		LottoBalls lottoBalls = new LottoBalls();
		assertThat(lottoBalls.pickRandomBalls()).size().isEqualTo(6);
	}
}
