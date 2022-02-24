package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Balls {
	private static final int SIZE = 6;
	private static final String SIZE_EXCEPTION = "숫자 요소는 6개여야 합니다.";

	private final List<Ball> balls;

	public Balls(final List<Integer> numbers) {
		validateNumbers(numbers);
		this.balls = numbers.stream()
			.map(Ball::new)
			.collect(Collectors.toUnmodifiableList());
	}

	private void validateNumbers(final List<Integer> numbers) {
		if (numbers == null) {
			throw new IllegalArgumentException(SIZE_EXCEPTION);
		}

		if (numbers.size() != SIZE) {
			throw new IllegalArgumentException(SIZE_EXCEPTION);
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
