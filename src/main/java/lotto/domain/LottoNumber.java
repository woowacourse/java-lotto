package lotto.domain;

import lotto.exceptions.LottoNumberException;

import java.util.*;

public class LottoNumber implements Comparable<LottoNumber> {
	public static final int MIN = 1;
	public static final int MAX = 45;
	private static final Map<Integer, LottoNumber> cachedLottoNumbers;

	static {
		cachedLottoNumbers = new HashMap<>();
		for (int i = MIN; i <= MAX; i++) {
			cachedLottoNumbers.put(i, new LottoNumber(i));
		}
	}

	private final int lottoNumber;

	private LottoNumber(final int lottoNumber) {
		checkIsWithinRange(lottoNumber);
		this.lottoNumber = lottoNumber;
	}

	public static LottoNumber of(final int lottoNumber) {
		checkIsWithinRange(lottoNumber);
		return cachedLottoNumbers.get(lottoNumber);
	}

	private static void checkIsWithinRange(int number) {
		if (number < MIN || number > MAX) {
			throw new LottoNumberException(number);
		}
	}

	public int getLottoNumber() {
		return lottoNumber;
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
	public int compareTo(LottoNumber lottoNumber) {
		return this.lottoNumber - lottoNumber.lottoNumber;
	}
}
