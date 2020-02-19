package lotto.domain;

import java.util.Objects;

import lotto.exception.InvalidNumberException;

public class Number {
	private final int number;
	public static final int MAX_LOTTO_NUMBER = 45;
	public static final int MIN_LOTTO_NUMBER = 1;

	public Number(String value) {
		validate(value);
		this.number = Integer.parseInt(value);
	}

	private void validate(String value) {

		checkNull(value);
		checkBlank(value);
		checkNumberFormat(value);
		checkRange(value);
	}

	private void checkNumberFormat(String value) {
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new InvalidNumberException("문자는 사용이 불가능합니다.");
		}
	}

	private void checkRange(String value) {
		int number = Integer.parseInt(value);
		if (number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER) {
			throw new InvalidNumberException("로또 번호는 1에서 45까지만 가능합니다");
		}
	}

	private void checkBlank(String value) {
		if (value.trim().isEmpty()) {
			throw new InvalidNumberException("공백은 사용이 불가능합니다.");
		}
	}

	private void checkNull(String value) {
		if (Objects.isNull(value)) {
			throw new InvalidNumberException("Null문자열은 사용이 불가능합니다.");
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
}
