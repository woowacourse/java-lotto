package lotto.domain;

import lotto.exceptions.LottoNumberIllegalArgumentException;

import java.util.*;
import java.util.stream.Collectors;

public class LottoNumber implements Comparable<LottoNumber> {
	private static final Map<Integer, LottoNumber> allLottoNumbers;

	private static final int MIN = 1;
	private static final int MAX = 45;

	private final int lottoNumber;

	LottoNumber(final int lottoNumber) {
		this.lottoNumber = lottoNumber;
	}

	static {
		Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();
		for (int i = MIN; i <= MAX; i++) {
			lottoNumbers.put(i, new LottoNumber(i));
		}
		allLottoNumbers = Collections.unmodifiableMap(lottoNumbers);
	}

	public static List<LottoNumber> getAll() {
		return allLottoNumbers.values()
				.stream()
				.collect(Collectors.toUnmodifiableList());
	}

	public static LottoNumber getLottoNumber(int number) {
		checkIsWithinRange(number);
		return allLottoNumbers.get(number);
	}

	private static void checkIsWithinRange(int number) {
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
