package lotto.domain;

import lotto.exception.InvalidLottoCountException;
import lotto.validator.Validator;

public class ManualLottoCount {
	private static final int MIN_COUNT = 0;

	private int count;

	public ManualLottoCount(String count) {
		Validator.validateInteger(count);
		this.count = Integer.parseInt(count);
		validOverZero(this.count);
	}

	public void validOverZero(int count) {
		if (count < MIN_COUNT) {
			throw new InvalidLottoCountException();
		}
	}

	public boolean hasNext() {
		return count > MIN_COUNT;
	}

	public void use() {
		count--;
	}
}
