package lotto.domain.ticket;

import java.util.Objects;

import lotto.domain.ticket.validator.BallValidator;

public class Ball {

	private final int number;

	public Ball(final int number) {
		BallValidator.validateBallNumber(number);
		this.number = number;
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		final Ball ball = (Ball)object;
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
