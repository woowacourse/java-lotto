package lotto.domain.number;

import lotto.exceptions.LottoNumberIllegalException;

import java.util.*;
import java.util.stream.Collectors;

public class LottoNumber implements Comparable<LottoNumber> {
	private static final Map<Integer, LottoNumber> LOTTO_NUMBERS;

	private static final int MIN_LOTTO_NUMBER = 1;
	private static final int MAX_LOTTO_NUMBER = 45;

	private final int lottoNumber;

	static {
		Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

		for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
			lottoNumbers.put(i, new LottoNumber(i));
		}

		LOTTO_NUMBERS = Collections.unmodifiableMap(lottoNumbers);
	}

	private LottoNumber(final int lottoNumber) {
		this.lottoNumber = lottoNumber;
	}

	public static LottoNumber of(final int lottoNumber) {
		checkIsValidRange(lottoNumber);

		return LOTTO_NUMBERS.get(lottoNumber);
	}

	public static List<LottoNumber> allLottoNumbers() {
		return LOTTO_NUMBERS.values()
				.stream()
				.collect(Collectors.toUnmodifiableList());
	}

	private static void checkIsValidRange(int lottoNumber) {
		if (lottoNumber < MIN_LOTTO_NUMBER || lottoNumber > MAX_LOTTO_NUMBER) {
			throw new LottoNumberIllegalException(lottoNumber);
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
		return "LottoNumber(" + lottoNumber + ")";
	}

	@Override
	public int compareTo(LottoNumber that) {
		return this.lottoNumber - that.lottoNumber;
	}
}
