package lotto.domain.lottoNumber;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
	public static final int MIN_LOTTO_NUMBER = 1;
	public static final int MAX_LOTTO_NUMBER = 45;

	private final int lottoNumber;

	LottoNumber(int lottoNumber) {
		validate(lottoNumber);
		this.lottoNumber = lottoNumber;
	}

	public static LottoNumber valueOf(int number) {
		if (number >= MIN_LOTTO_NUMBER && number <= MAX_LOTTO_NUMBER) {
			return LottoNumberCache.asLottoNumber(number);
		}
		return new LottoNumber(number);
	}

	public static LottoNumber valueOf(String inputNumber) {
		return valueOf(parseToInt(inputNumber));
	}

	private static int parseToInt(String inputNumber) {
		try {
			return Integer.parseInt(inputNumber);
		} catch (NumberFormatException e) {
			throw new InvalidLottoNumberException(InvalidLottoNumberException.NOT_INTEGER);
		}
	}

	private void validate(int number) {
		if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
			throw new InvalidLottoNumberException(InvalidLottoNumberException.OUT_OF_BOUND_LOTTO_NUMBER);
		}
	}

	public int getLottoNumber() {
		return lottoNumber;
	}

	@Override
	public int compareTo(LottoNumber that) {
		return Integer.compare(this.lottoNumber, that.lottoNumber);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		LottoNumber that = (LottoNumber)object;
		return this.lottoNumber == that.lottoNumber;
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
