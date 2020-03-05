package lotto.domain;

import lotto.exceptions.LottoNumberIllegalArgumentException;

import java.util.*;

public class LottoNumber implements Comparable<LottoNumber> {
	private static final int MIN = 1;
	private static final int MAX = 45;
	private static final Map<Integer, LottoNumber> cachedLottoNumbers;

	static {
		cachedLottoNumbers = new HashMap<>();
		for (int i = MIN; i <= MAX; i++) {
			cachedLottoNumbers.put(i, new LottoNumber(i));
		}
	}

	private final int lottoNumber;

	public LottoNumber(final int lottoNumber) throws LottoNumberIllegalArgumentException {
		checkIsWithinRange(lottoNumber);
		this.lottoNumber = lottoNumber;
	}

	public static List<LottoNumber> getAll() {
		return new ArrayList<>(cachedLottoNumbers.values());
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
	public int compareTo(LottoNumber lottoNumber) {
		return this.lottoNumber - lottoNumber.lottoNumber;
	}
}
