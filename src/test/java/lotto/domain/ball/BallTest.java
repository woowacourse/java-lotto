package lotto.domain.ball;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import lotto.domain.ball.Ball;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BallTest {

	@DisplayName("볼은 1~45 범위의 숫자여야 한다.")
	@ParameterizedTest
	@ValueSource(ints = {46, 47, 48})
	void rangeOutExceptionTest(final int number) {
		assertThatThrownBy(() -> new Ball(number))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("생성자 기능 테스트")
	@ParameterizedTest
	@ValueSource(ints = {43, 44, 45})
	void initTest(final int number) {
		assertDoesNotThrow(() -> new Ball(number));
	}

	@DisplayName("숫자 비교 기능 테스트")
	@Test
	void equalsNumberTest() {
		final Ball ball = new Ball(40);
		final Ball ball2 = new Ball(40);
		assertThat(ball.equals(ball2)).isTrue();
	}
}
