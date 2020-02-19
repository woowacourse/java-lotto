package lotto.domain;

public class Money {
	private static final int ZERO = 0;
	private static final int UNIT = 1000;
	private static final int MAX_BOUND = 100000;

	private final int money;

	public Money(String money) {
		this.money = validate(money);
	}

	private int validate(String money) {
		validateNullOrEmpty(money);
		int parsedMoney = parseToInteger(money);
		validatePositive(parsedMoney);
		validateUnit(parsedMoney);
		validateMaxBound(parsedMoney);
		return parsedMoney;
	}

	private void validateNullOrEmpty(String money) {
		if (money == null || money.isEmpty()) {
			throw new InvalidMoneyException(InvalidMoneyException.NULL_OR_EMPTY);
		}
	}

	private int parseToInteger(String money) {
		try {
			return Integer.parseInt(money);
		} catch (NumberFormatException ne) {
			throw new InvalidMoneyException(InvalidMoneyException.NOT_INTEGER);
		}
	}

	private void validatePositive(int parsedMoney) {
		if (parsedMoney <= ZERO) {
			throw new InvalidMoneyException(InvalidMoneyException.NOT_POSITIVE);
		}
	}

	private void validateUnit(int parsedMoney) {
		if (parsedMoney % UNIT != ZERO) {
			throw new InvalidMoneyException(InvalidMoneyException.INVALID_UNIT);
		}
	}

	private void validateMaxBound(int parsedMoney) {
		if (parsedMoney > MAX_BOUND) {
			throw new InvalidMoneyException(InvalidMoneyException.OUT_OF_BOUND);
		}
	}
}
