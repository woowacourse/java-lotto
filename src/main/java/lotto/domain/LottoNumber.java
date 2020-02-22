package lotto.domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
	public static final int MINIMUM = 1;
	public static final int MAXIMUM = 45;

	private final int number;

	public LottoNumber(final int number) {
		validateNumberRange(number);
		this.number = number;
	}

	private void validateNumberRange(int number) {
		if (isWrongRange(number)) {
			throw new IllegalArgumentException(
				String.format("로또 번호는 %d이상 %d이하여야 합니다.", MINIMUM, MAXIMUM));
		}
	}

	private boolean isWrongRange(int number) {
		return number < MINIMUM || number > MAXIMUM;
	}

	@Override
	public int compareTo(LottoNumber lottoNumber) {
		return this.number - lottoNumber.number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LottoNumber that = (LottoNumber)o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public String toString() {
		return "" + number;
	}
}
