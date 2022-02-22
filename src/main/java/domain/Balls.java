package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Balls {

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
			throw new IllegalArgumentException("숫자 요소는 6개여야 합니다.");
		}
	}

	private void validateSize(final List<Integer> numbers) {
		if (numbers.size() != 6) {
			throw new IllegalArgumentException("숫자 요소는 6개여야 합니다.");
		}
	}

	public boolean contains(Ball ball) {
		return balls.contains(ball);
	}
}
