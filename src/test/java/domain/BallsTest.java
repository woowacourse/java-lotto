package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

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

	@DisplayName("볼 포함 여부 확인 테스트")
	@Test
	void containsTest() {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		Balls balls = new Balls(numbers);

		Ball ball = new Ball(1);
		assertThat(balls.contains(ball)).isTrue();
	}

	@DisplayName("당첨 번호와 일치 개수 확인")
	@Test
	void countMatches() {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		Balls balls = new Balls(numbers);

		List<Integer> numbers2 = Arrays.asList(2,3,4,6,29,30);
		Balls answer = new Balls(numbers2);

		assertThat(balls.countMatches(answer)).isEqualTo(4);
	}
}
