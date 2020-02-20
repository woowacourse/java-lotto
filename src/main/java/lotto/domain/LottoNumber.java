package lotto.domain;

import lotto.exceptions.LottoNumberIllegalArgumentException;

import java.util.Objects;

public class LottoNumber {
	private static final int MIN = 1;
	private static final int MAX = 60;

	private final int lottoNumber;

	public LottoNumber(final int lottoNumber) throws LottoNumberIllegalArgumentException {
		checkIsWithinRange(lottoNumber);
		this.lottoNumber = lottoNumber;
	}

	private void checkIsWithinRange(int number) {
		if (number < MIN || number > MAX) {
			throw new LottoNumberIllegalArgumentException(number);
		}
	}

	public static int compare(LottoNumber a, LottoNumber b) {
		return a.lottoNumber - b.lottoNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LottoNumber that = (LottoNumber) o;
		return lottoNumber == that.lottoNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lottoNumber);
	}

	@Override
	public String toString() {
		return Integer.toString(lottoNumber);
	}
}
