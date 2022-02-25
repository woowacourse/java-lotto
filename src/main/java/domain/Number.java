package domain;

import java.util.Objects;

public class Number {

	private static final int MIN_LOTTO_NUMBER = 1;
	private static final int MAX_LOTTO_NUMBER = 45;
	private static final String OUT_OF_RANGE_EXCEPTION_MESSAGE = "번호는 1 ~ 45의 숫자여야 합니다";

	private final int number;

	public Number(int number) {
		checkNumberRange(number);
		this.number = number;
	}

	private void checkNumberRange(int number) {
		if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
			throw new IllegalArgumentException(OUT_OF_RANGE_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Number number1 = (Number)o;
		return number == number1.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	public int getNumber() {
		return number;
	}
}
