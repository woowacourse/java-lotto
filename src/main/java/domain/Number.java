package domain;

import java.util.Objects;

public class Number {

	private final int number;

	public Number(int number) {
		checkNumberRange(number);
		this.number = number;
	}

	public static Number from(String userInput) {
		checkNotDigit(userInput);
		return new Number(Integer.parseInt(userInput));
	}

	private void checkNumberRange(int number) {
		if (number < 1 || number > 45) {
			throw new IllegalArgumentException("번호는 1 ~ 45의 숫자여야 합니다");
		}
	}

	private static void checkNotDigit(String userInput) {
		try {
			Integer.parseInt(userInput);
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException("숫자만 입력해야 합니다");
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
