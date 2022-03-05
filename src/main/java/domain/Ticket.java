package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Ticket {
	public static final int PRICE = 1000;
	public static final int SIZE = 6;

	public static final String SIZE_ERROR = "로또 번호는 6자리여야 합니다.";

	private final List<Ball> balls;

	public Ticket(final List<Integer> numbers) {
		validateBalls(numbers);
		this.balls = makeNumbersToBalls(List.copyOf(numbers));
	}

	public static void validateBalls(final List<Integer> numbers) {
		if (numbers.size() != SIZE) {
			throw new IllegalArgumentException(SIZE_ERROR);
		}
	}

	private List<Ball> makeNumbersToBalls(final List<Integer> numbers) {
		return numbers.stream()
			.map(Ball::from)
			.collect(Collectors.toUnmodifiableList());
	}

	public int countMatch(final List<Ball> balls) {
		return (int)balls.stream()
			.filter(this::contains)
			.count();
	}

	public boolean contains(Ball ball) {
		return balls.contains(ball);
	}

	public List<Ball> getBalls() {
		return Collections.unmodifiableList(balls);
	}
}
