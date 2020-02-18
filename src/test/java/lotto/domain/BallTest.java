package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BallTest {

	@DisplayName("볼 가져오기 범위 외 숫자")
	@ParameterizedTest
	@ValueSource(ints = {-1, 46})
	void getBallOutOfRange(int number) {
		assertThatThrownBy(() -> Ball.of(number))
		.isInstanceOf(IllegalArgumentException.class);
	}
}