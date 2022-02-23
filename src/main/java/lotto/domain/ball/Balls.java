package lotto.domain.ball;

import java.util.List;
import java.util.stream.Collectors;

public class Balls {

	private static final int BALLS_DEFAULT_SIZE = 6;
	private static final String BALLS_NULL_POINTER_EXCEPTION_MESSAGE = "숫자 요소는 NULL이 아니어야 합니다.";
	private static final String BALLS_OUT_OF_SIZE_EXCEPTION_MESSAGE = "숫자 요소는 6개여야 합니다.";

	private final List<Ball> balls;

	public Balls(final List<Integer> numbers) {
		this.validateNumbers(numbers);
		this.balls = numbers.stream()
			.map(Ball::new)
			.collect(Collectors.toUnmodifiableList());
	}

	private void validateNumbers(final List<Integer> numbers) {
		this.validateNull(numbers);
		this.validateSize(numbers);
	}

	private void validateNull(final List<Integer> numbers) {
		if (numbers == null) {
			throw new IllegalArgumentException(BALLS_NULL_POINTER_EXCEPTION_MESSAGE);
		}
	}

	private void validateSize(final List<Integer> numbers) {
		if (numbers.size() != BALLS_DEFAULT_SIZE) {
			throw new IllegalArgumentException(BALLS_OUT_OF_SIZE_EXCEPTION_MESSAGE);
		}
	}

	public boolean contains(Ball ball) {
		return balls.contains(ball);
	}

	public int countMatches(Balls answer) {
		return (int)this.balls.stream()
			.filter(answer::contains)
			.count();
	}

	public List<Integer> getBallNumbers() {
		return balls.stream()
			.map(Ball::getNumber)
			.collect(Collectors.toUnmodifiableList());
	}

}
