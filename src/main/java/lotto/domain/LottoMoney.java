package lotto.domain;

public class LottoMoney {
	private static final int ZERO = 0;
	private static final int UNIT = 1000;
	private static final int MAX_BOUND = 100000;
	public static final int LOTTO_PRICE = 1000;

	private final int money;

	public LottoMoney(String money) {
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
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NULL_OR_EMPTY);
		}
	}

	private int parseToInteger(String money) {
		try {
			return Integer.parseInt(money);
		} catch (NumberFormatException ne) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NOT_INTEGER);
		}
	}

	private void validatePositive(int parsedMoney) {
		if (parsedMoney <= ZERO) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NOT_POSITIVE);
		}
	}

	private void validateUnit(int parsedMoney) {
		if (parsedMoney % UNIT != ZERO) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.INVALID_UNIT);
		}
	}

	private void validateMaxBound(int parsedMoney) {
		if (parsedMoney > MAX_BOUND) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.OUT_OF_BOUND);
		}
	}

	public int purchaseLotto() {
		return money / LOTTO_PRICE;
	}
}
