package domain;

import java.util.Objects;

public class Ball {
	private final int number;

	public Ball(int number) {
		validateNumberIsInRange(number);

		this.number = number;
	}

	private void validateNumberIsInRange(int number) {
		if (number < 1 || number > 45) {
			throw new IllegalArgumentException("숫자의 범위는 1부터 45까지여야 합니다.");
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
}
