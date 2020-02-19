package lotto.domain;

import java.util.Objects;

public class LottoNumber {
	public static final int MAX_NUMBER = 45;
	public static final int MIN_NUMBER = 1;

	private final int number;

	public LottoNumber(final int number) {
		checkValidationOf(number);
		this.number = number;
	}

	private void checkValidationOf(final int number) {
		if (number > MAX_NUMBER) {
			throw new IllegalArgumentException("로또 숫자는 45를 넘기면 안됩니다.");
		}
		if (number < MIN_NUMBER) {
			throw new IllegalArgumentException("로또 숫자는 0이하 일 수 없습니다.");
		}
	}

	@Override
	public String toString() {
		return Integer.toString(number);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber that = (LottoNumber)o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
