package lotto.domain;

import lotto.exceptions.LottoNumberIllegalArgumentException;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
	static final int MIN = 1;
	static final int MAX = 45;

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

	@Override
	public int compareTo(LottoNumber o) {
		return lottoNumber - o.lottoNumber;
	}
}
