package lotto.domain;

public class Number {
	private static final int MIN_BOUND = 1;
	private static final int MAX_BOUND = 45;

	private final int number;

	public Number(int number) {
		validateBound(number);
		this.number = number;
	}

	private void validateBound(int number) {
		if (number < MIN_BOUND || number > MAX_BOUND) {
			throw new InvalidNumberException(InvalidNumberException.OUT_OF_BOUND);
		}
	}
}
