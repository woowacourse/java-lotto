package lotto.domain;

import java.util.Objects;

import lotto.exception.InvalidRangeException;
import lotto.validator.Validator;

public class LottoNo implements Comparable<LottoNo> {
	public static final int MIN = 1;
	public static final int MAX = 45;

	private final int number;

	public LottoNo(String numberString) {
		Validator.validateInteger(numberString);
		int number = Integer.parseInt(numberString);
		validateLottoRange(number);
		this.number = number;
	}

	private void validateLottoRange(int number) {
		if (isNotLottoRange(number)) {
			throw new InvalidRangeException();
		}
	}

	private boolean isNotLottoRange(int number) {
		return number < MIN || number > MAX;
	}

	@Override
	public int compareTo(LottoNo other) {
		return this.number - other.number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNo lottoNo = (LottoNo)o;
		return number == lottoNo.number;
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
