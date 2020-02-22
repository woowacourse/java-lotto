package lotto.domain;

import java.util.Iterator;

public class PurchasingAmount implements Iterator<Integer> {
	private static final int PURCHASE_UNIT = 1000;
	private static final int ZERO = 0;

	private int amount;

	public PurchasingAmount(int amount) {
		checkValidationOf(amount);
		this.amount = amount;
	}

	private void checkValidationOf(int amount) {
		if (amount == ZERO) {
			throw new IllegalArgumentException("최소 한장이상 구매 하셔야 합니다.");
		}
		if (hasChangeMoney(amount)) {
			throw new IllegalArgumentException("구입 금액은 1000원 단위이어야 합니다.");
		}
		if (amount < ZERO) {
			throw new IllegalArgumentException("구입 금액은 음수가 될 수 없습니다.");
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
