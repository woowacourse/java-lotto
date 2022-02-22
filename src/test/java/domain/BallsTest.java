package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class BallsTest {

	@DisplayName("생성자 매개변수가 null이면 예외 발생")
	@ParameterizedTest
	@NullSource
	void nullExceptionTest(List<Integer> numbers) {
		assertThatThrownBy(() -> new Balls(numbers))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("볼이 6개인지 테스트")
	@Test
	void sizeTest() {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7);

		assertThatThrownBy(() -> new Balls(numbers))
			.isInstanceOf(IllegalArgumentException.class);
	}

}
