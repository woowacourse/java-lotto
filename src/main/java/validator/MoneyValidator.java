package validator;

import static utils.Messages.*;

public class MoneyValidator {
	public static void validate(int money) {
		isOverThousand(money);
		isDivideByThousand(money);
	}

	private static void isOverThousand(int money) {
		if (money < 1000) {
			throw new IllegalArgumentException(MONEY_OVER_THOUSANDS_ERROR_MESSAGE);
		}
	}

	private static void isDivideByThousand(int money) {
		if (money % 1000 != 0) {
			throw new IllegalArgumentException(MONEY_DIVIDE_ERROR_MESSAGE);
		}
	}
}