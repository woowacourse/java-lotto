package lotto.domain;

import lotto.exceptions.BallNumberOutOfRangeException;

public class LottoNumber {
	private static final int MIN = 1;
	private static final int MAX = 60;

	private int lottoNumber;

	public LottoNumber(int lottoNumber) throws BallNumberOutOfRangeException {
		checkIsWithinRange(lottoNumber);
		this.lottoNumber = lottoNumber;
	}

	private void checkIsWithinRange(int number) {
		if (number < MIN || number > MAX) {
			throw new BallNumberOutOfRangeException(number);
		}
	}
}
