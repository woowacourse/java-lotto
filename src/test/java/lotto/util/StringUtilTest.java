package lotto.util;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.ticket.LottoBall;

class StringUtilTest {
	@DisplayName("하나의 문자열을 구분자를 기준으로 분리하는 기능 확인")
	@Test
	void splitNameTest() {
		Set<LottoBall> balls = new TreeSet<>(
			Arrays.asList(LottoBall.valueOf(1), LottoBall.valueOf(2), LottoBall.valueOf(3)));
		String result = StringUtil.parseBalls(balls);
		assertThat(result).isEqualTo("1, 2, 3");
	}
}