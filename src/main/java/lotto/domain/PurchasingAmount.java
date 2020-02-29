package lotto.domain;

import java.util.Iterator;

public class PurchasingAmount implements Iterator<Integer> {
	private static final String CANNOT_BE_ZERO = "최소 한장이상 구매 하셔야 합니다.";
	private static final String HAS_NOT_CHANGE = "구입 금액은 1000원 단위이어야 합니다.";
	private static final String CANNOT_BE_NEGATIVE = "구입 금액은 음수가 될 수 없습니다.";
	private static final int PURCHASE_UNIT = 1000;
	private static final int ZERO = 0;

	private int amount;

	public PurchasingAmount(int amount) {
		checkValidationOf(amount);
		this.amount = amount;
	}

	private void checkValidationOf(int amount) {
		if (amount == ZERO) {
			throw new IllegalArgumentException(CANNOT_BE_ZERO);
		}
		if (hasChangeMoney(amount)) {
			throw new IllegalArgumentException(HAS_NOT_CHANGE);
		}
		if (amount < ZERO) {
			throw new IllegalArgumentException(CANNOT_BE_NEGATIVE);
		}
	}

	private boolean hasChangeMoney(int amount) {
		return amount % PURCHASE_UNIT != ZERO;
	}

	@Override
	public boolean hasNext() {
		return amount >= PURCHASE_UNIT;
	}

	@Override
	public Integer next() {
		return amount -= PURCHASE_UNIT;
	}
}
