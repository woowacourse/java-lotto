package lotto.domain;

public class PurchasingAmount {
	public static final int PURCHASE_UNIT = 1000;
	public static final int ZERO = 0;
	private final int amount;

	public PurchasingAmount(final int amount) {
		checkValidationOf(amount);
		this.amount = amount;
	}

	private void checkValidationOf(final int amount) {
		if (amount == 0) {
			throw new IllegalArgumentException("최소 한장이상 구매 하셔야 합니다.");
		}
		if (hasChangeMoney(amount)) {
			throw new IllegalArgumentException("구입 금액은 1000원 단위이어야 합니다.");
		}
		if (amount < 0) {
			throw new IllegalArgumentException("구입 금액은 음수가 될 수 없습니다.");
		}
	}

	private boolean hasChangeMoney(final int amount) {
		return amount % PURCHASE_UNIT != ZERO;
	}
}
