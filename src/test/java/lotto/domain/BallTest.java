package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BallTest {
	@DisplayName("로또 생성 범위 외 숫자 가져오면 예외 발생 확")
	@ParameterizedTest
	@ValueSource(ints = {0, 46})
	void getBallOutOfRangeTest(int number) {
		assertThatThrownBy(() -> Ball.valueOf(number))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("볼 비교인 확인")
	@ParameterizedTest
	@CsvSource(value = {"1,1,true", "40,40,true", "1,30,false", "40,45,false"})
	void vsBall(int firstBallNo, int secondBallNo, boolean expected) {
		Ball firstBall = Ball.valueOf(firstBallNo);
		Ball secondBall = Ball.valueOf(secondBallNo);
		assertThat(firstBall.equals(secondBall)).isEqualTo(expected);
	}
}