package lotto.domain.ball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

class BallsTest {

	@DisplayName("숫자 요소는 NULL이 아니어야 합니다.")
	@ParameterizedTest
	@NullSource
	void numbersNullExceptionTest(final List<Integer> numbers) {
		assertThatThrownBy(() -> new Balls(numbers))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("숫자 요소는 6개여야 합니다.")
	@ParameterizedTest
	@MethodSource("provideForNumbersOutOfSizeExceptionTest")
	void numbersOutOfSizeExceptionTest(final List<Integer> numbers) {
		assertThatThrownBy(() -> new Balls(numbers))
			.isInstanceOf(IllegalArgumentException.class);
	}

	public static Stream<Arguments> provideForNumbersOutOfSizeExceptionTest() {
		return Stream.of(
				Arguments.of(Named.of("요소 5개", Arrays.asList(1, 2, 3, 4, 5))),
				Arguments.of(Named.of("요소 7개", Arrays.asList(1, 2, 3, 4, 5, 6, 7))),
				Arguments.of(Named.of("요소 8개", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)))
		);
	}

	@DisplayName("볼 포함 여부 확인 테스트")
	@Test
	void containsTest() {
		final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		final Balls balls = new Balls(numbers);

		final Ball ball = new Ball(1);
		assertThat(balls.contains(ball)).isTrue();
	}

	@DisplayName("당첨 번호와 일치 개수 확인")
	@Test
	void countMatches() {
		final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		final Balls balls = new Balls(numbers);

		final List<Integer> numbers2 = Arrays.asList(2, 3, 4, 6, 29, 30);
		final Balls answer = new Balls(numbers2);

		assertThat(balls.countMatches(answer)).isEqualTo(4);
	}

}
