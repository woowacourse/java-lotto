package domain;

import java.util.Objects;

public class LottoNumber {

	public static final int FIRST_LOTTO_NUMBER = 1;
	public static final int LAST_LOTTO_NUMBER = 45;
	private static final String NOT_NUMBER_IN_LOTTO_NUMBER_RANGE_MESSAGE = "번호는 1 ~ 45의 숫자여야 합니다";
	private static final String NOT_NUMBER_DIGIT_MESSAGE = "숫자만 입력해야 합니다";
	private final int number;

	public LottoNumber(final int number) {
		checkNumberRange(number);
		this.number = number;
	}

	public static LottoNumber from(final String userInput) {
		checkNotDigit(userInput);
		return new LottoNumber(Integer.parseInt(userInput));
	}

	private static void checkNotDigit(final String userInput) {
		try {
			Integer.parseInt(userInput);
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException(NOT_NUMBER_DIGIT_MESSAGE);
		}
	}

	private void checkNumberRange(final int number) {
		if (number < FIRST_LOTTO_NUMBER || number > LAST_LOTTO_NUMBER) {
			throw new IllegalArgumentException(NOT_NUMBER_IN_LOTTO_NUMBER_RANGE_MESSAGE);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoNumber lottoNumber1 = (LottoNumber)o;
		return number == lottoNumber1.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	public int getNumber() {
		return number;
	}
}
