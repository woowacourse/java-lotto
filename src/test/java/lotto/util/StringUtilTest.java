package lotto.util;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.domain.Ball;

class StringUtilTest {
	@Test
	void name() {
		List<Ball> balls = Arrays.asList(Ball.of(1), Ball.of(2), Ball.of(3));
		String result = StringUtil.parseBalls(balls);
		assertThat(result).isEqualTo("1, 2, 3");
	}
}