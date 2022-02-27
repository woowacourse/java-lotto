package domain;

import java.util.Objects;

public class Ball {
	private static final int START_RANGE = 1;
	private static final int END_RANGE = 45;

	private static final String RANGE_EXCEPTION = "숫자의 범위는 1부터 45까지여야 합니다.";

	private final int number;

	public Ball(int number) {
		validateNumberIsInRange(number);
		this.number = number;
	}

	private void validateNumberIsInRange(int number) {
		if (number < START_RANGE || number > END_RANGE) {
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

	public int getNumber() {
		return this.number;
	}
}
