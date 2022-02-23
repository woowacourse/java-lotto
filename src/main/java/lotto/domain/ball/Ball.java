package lotto.domain.ball;

import java.util.Objects;

public class Ball {

	private static final int BALL_RANGE_INCLUSIVE_START = 1;
	private static final int BALL_RANGE_INCLUSIVE_END = 45;
	private static final String BALL_OUT_OF_RANGE_EXCEPTION_MESSAGE = "번호의 범위는 1부터 45까지여야 합니다.";

	private final int number;

	public Ball(int number) {
		validateNumberIsInRange(number);

		this.number = number;
	}

	private void validateNumberIsInRange(int number) {
		if (number < BALL_RANGE_INCLUSIVE_START || number > BALL_RANGE_INCLUSIVE_END) {
			throw new IllegalArgumentException(BALL_OUT_OF_RANGE_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Ball ball = (Ball)object;
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
