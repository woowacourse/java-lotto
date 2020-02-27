package lotto.domain.lotto;

import java.util.Objects;

import lotto.domain.exception.InvalidLottoMoneyException;

public class LottoMoney {
	public static final int ZERO = 0;
	private static final int MAX_BOUND = 100_000;
	private static final int LOTTO_PRICE = 1_000;

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

	private void validatePositive(long parsedMoney) {
		if (parsedMoney <= ZERO) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.NOT_POSITIVE);
		}
	}

	private void validateUnit(long parsedMoney) {
		if (parsedMoney % LOTTO_PRICE != ZERO) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.INVALID_UNIT);
		}
	}

	private void validateMaxBound(long parsedMoney) {
		if (parsedMoney > MAX_BOUND) {
			throw new InvalidLottoMoneyException(InvalidLottoMoneyException.OUT_OF_BOUND);
		}
	}

	public int getPurchasedLottoCount() {
		return money / LOTTO_PRICE;
	}

	public int getMoney() {
		return money;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LottoMoney that = (LottoMoney)o;
		return money == that.money;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}
}
