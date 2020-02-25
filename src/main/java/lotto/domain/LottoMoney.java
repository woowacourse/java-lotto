package lotto.domain;

import java.util.Objects;

public class LottoMoney {
	private static final long ZERO = 0;
	private static final long MAX_BOUND = 100000;
	private static final long LOTTO_PRICE = 1000;
	private static final int PERCENT = 100;

	private final long money;

	public LottoMoney(String money) {
		this.money = validate(money);
	}

	LottoMoney(long money) {
		this.money = money;
	}

	private long validate(String money) {
		validateNullOrEmpty(money);
		long parsedMoney = parseToLong(money);
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

	private long parseToLong(String money) {
		try {
			return Long.parseLong(money);
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
		return (int)(money / LOTTO_PRICE);
	}

	public LottoMoney add(LottoMoney addedLottoMoney) {
		return new LottoMoney(this.money + addedLottoMoney.money);
	}

	public LottoMoney multiply(int multiplyCount) {
		return new LottoMoney(this.money * multiplyCount);
	}

	public long getMoney() {
		return money;
	}

	public int getWinningRatio(LottoMoney inputLottoMoney) {
		return (int)((this.money * PERCENT) / inputLottoMoney.money);
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
