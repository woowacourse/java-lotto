package lotto.domain;

import java.util.Objects;

import lotto.exception.InvalidRangeException;

public class LottoNo implements Comparable<LottoNo> {
	public static final int MIN_LOTTO_NO = 1;
	public static final int MAX_LOTTO_NO = 45;

	private final int number;

	public LottoNo(int number) {
		validateLottoRange(number);
		this.number = number;
	}

	private void validateLottoRange(int number) {
		if (isLottoRange(number)) {
			throw new InvalidRangeException();
		}
	}

	private boolean isLottoRange(int number) {
		return number < MIN_LOTTO_NO || number > MAX_LOTTO_NO;
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
