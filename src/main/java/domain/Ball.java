package domain;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ball {
	public static final int MIN_NUMBER = 1;
	public static final int MAX_NUMBER = 45;
	private static final String RANGE_EXCEPTION = "숫자의 범위는 1부터 45까지여야 합니다.";

	private static final Map<Integer, Ball> BALL_CACHE;

	static {
		BALL_CACHE = IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
			.boxed()
			.collect(Collectors.toMap(number -> number, Ball::new));
	}

	private final int number;

	private Ball(final int number) {
		this.number = number;
	}

	public static Ball from(final int number) {
		validateNumberIsInRange(number);
		return BALL_CACHE.get(number);
	}

	private static void validateNumberIsInRange(final int number) {
		if (number < MIN_NUMBER || number > MAX_NUMBER) {
			throw new IllegalArgumentException(RANGE_EXCEPTION);
		}
	}

	public int getNumber() {
		return number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Ball ball = (Ball)o;
		return number == ball.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
