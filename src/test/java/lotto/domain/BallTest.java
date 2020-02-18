package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BallTest {

	@DisplayName("볼 가져오기 범위 외 숫자")
	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	void getBallOutOfRange(int number) {
		assertThatThrownBy(() -> Ball.of(number))
		.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("볼 비교")
	@ParameterizedTest
	@CsvSource(value = {"1,1,true", "40,40,true", "1,30,false", "40,45,false"})
	void vsBall(int firstBallNo, int secondBallNo, boolean expected) {
		Ball firstBall = Ball.of(firstBallNo);
		Ball secondBall = Ball.of(secondBallNo);
		assertThat(firstBall.equals(secondBall)).isEqualTo(expected);
	}
}