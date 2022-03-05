package lotto.model;

public class Money {
	private static final String ERROR_NEGATIVE = "[ERROR] 구매 금액은 0원보다 커야 합니다";
	private static final String ERROR_UNIT = "[ERROR] 구매 금액은 1000원 단위로 입력하세요";
	private static final String ERROR_TYPE = "[ERROR] 구매 금액은 숫자로만 입력하세요";
	private static final int UNIT = 1000;

	private final int amount;

	private Money(int amount) {
		checkNegative(amount);
		checkUnit(amount);

		this.amount = amount;
	}

	private void checkNegative(int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(ERROR_NEGATIVE);
		}
	}

	private void checkUnit(int amount) {
		if (amount % UNIT != 0) {
			throw new IllegalArgumentException(ERROR_UNIT);
		}
	}

	public static Money from(String input) {
		try {
			return new Money(Integer.parseInt(input));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_TYPE);
		}
	}

	public double rate(int numerator) {
		return Math.ceil((double)numerator / this.amount * 1000) / 1000.0;
	}

	public int countAvailable(int price) {
		return this.amount / price;
	}
}
