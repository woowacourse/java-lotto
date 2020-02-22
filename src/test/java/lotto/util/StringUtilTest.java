package lotto.util;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import lotto.domain.Ball;

class StringUtilTest {
	@Test
	void name() {
		List<Ball> balls = Arrays.asList(Ball.valueOf(1), Ball.valueOf(2), Ball.valueOf(3));
		String result = StringUtil.parseBalls(balls);
		assertThat(result).isEqualTo("1, 2, 3");
	}
}