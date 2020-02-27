package lotto.domain;

import java.util.Objects;

public class LottoNumber {
	private static final String CANNOT_BE_CHARACTER = "로또 숫자는 문자가 될 수 없습니다.";
	private static final String CANNOT_OVER_MAX_NUMBER = "로또 숫자는 45를 넘기면 안됩니다.";
	private static final String CANNOT_UNDER_MIN_NUMBER = "로또 숫자는 0이하 일 수 없습니다.";
	private static final int MAX_NUMBER = 45;
	private static final int MIN_NUMBER = 1;

	private final int number;

	public LottoNumber(String number) {
		checkValidationOf(number);
		this.number = Integer.parseInt(number);
	}

	private void checkValidationOf(String number) {
		int digit;

		try {
			digit = Integer.parseInt(number);
		} catch (NumberFormatException e) {
			throw new NumberFormatException(CANNOT_BE_CHARACTER);
		}
		if (digit > MAX_NUMBER) {
			throw new IllegalArgumentException(CANNOT_OVER_MAX_NUMBER);
		}
		if (digit < MIN_NUMBER) {
			throw new IllegalArgumentException(CANNOT_UNDER_MIN_NUMBER);
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
