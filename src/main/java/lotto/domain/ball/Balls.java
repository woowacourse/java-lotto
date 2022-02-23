package lotto.domain.ball;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.ball.validator.BallsValidator;

public class Balls {

	private final List<Ball> balls;

	public Balls(final List<Integer> numbers) {
		BallsValidator.validateBallNumbers(numbers);
		this.balls = numbers.stream()
			.map(Ball::new)
			.collect(Collectors.toUnmodifiableList());
	}

	public boolean contains(final Ball ball) {
		return balls.contains(ball);
	}

	public int countMatches(final Balls answer) {
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
