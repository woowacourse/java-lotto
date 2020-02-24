package lotto.domain.lottonumber;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
	static final int MIN_BOUND = 1;
	static final int MAX_BOUND = 45;

	private final int number;

	LottoNumber(int number) {
		validateBound(number);
		this.number = number;
	}

	private void validateBound(int number) {
		if (number < MIN_BOUND || number > MAX_BOUND) {
			throw new InvalidLottoNumberException(InvalidLottoNumberException.OUT_OF_BOUND);
		}
	}

	public static LottoNumber valueOf(int number) {
		return LottoNumberCache.getNumber(number);
	}

	public static LottoNumber valueOf(String inputNumber) {
		return valueOf(parseToInt(inputNumber));
	}

	private static int parseToInt(String inputNumber) {
		try {
			return Integer.parseInt(inputNumber);
		} catch (NumberFormatException nfe) {
			throw new InvalidLottoNumberException(InvalidLottoNumberException.NOT_INTEGER);
		}
	}

	public int getNumber() {
		return number;
	}

	@Override
	public int compareTo(LottoNumber o) {
		return number - o.number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber lottoNumber = (LottoNumber)o;
		return this.number == lottoNumber.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
