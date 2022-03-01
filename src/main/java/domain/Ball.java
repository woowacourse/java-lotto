package domain;

import java.util.Objects;

public class Ball {
	public static final int MIN_NUMBER = 1;
	public static final int MAX_NUMBER = 45;

	private static final String RANGE_EXCEPTION = "숫자의 범위는 1부터 45까지여야 합니다.";

	private final int number;

	public Ball(final int number) {
		validateNumberIsInRange(number);
		this.number = number;
	}

	private void validateNumberIsInRange(int number) {
		if (number < MIN_NUMBER || number > MAX_NUMBER) {
			throw new IllegalArgumentException(RANGE_EXCEPTION);
		}
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

	@Override
	public String toString() {
		return String.valueOf(number);
	}
}
