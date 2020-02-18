package lotto.domain;

import lotto.exceptions.BallNumberOutOfRangeException;

public class BallNumber {
	private static final int MIN = 1;
	private static final int MAX = 60;

	private int ballNumber;

	public BallNumber(int ballNumber) throws BallNumberOutOfRangeException {
		checkIsWithinRange(ballNumber);
		this.ballNumber = ballNumber;
	}

	private void checkIsWithinRange(int ballNumber) {
		if (ballNumber < MIN || ballNumber > MAX) {
			throw new BallNumberOutOfRangeException(ballNumber);
		}
	}
}
