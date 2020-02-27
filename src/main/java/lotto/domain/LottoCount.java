package lotto.domain;

public class LottoCount {
	private static final int MINIMUM_MANUAL_LOTTO_COUNT = 0;

	private final int manualLottoCount;
	private final int autoLottoCount;

	public LottoCount(Money money, int manualLottoCount) {
		validate(money, manualLottoCount);

		this.manualLottoCount = manualLottoCount;
		this.autoLottoCount = calculateTotalLottoCount(money) - this.manualLottoCount;
	}

	private void validate(Money money, int manualLottoCount) {
		validateRangeOfManualLottoCount(money, manualLottoCount);
	}

	private void validateRangeOfManualLottoCount(Money money, int manualLottoCount) {
		if (isInvalidRange(money, manualLottoCount)) {
			throw new IllegalArgumentException("구매할 수 있는 수동 로또 범위가 아닙니다!");
		}
	}

	private boolean isInvalidRange(Money money, int manualLottoCount) {
		return manualLottoCount < MINIMUM_MANUAL_LOTTO_COUNT || manualLottoCount > calculateTotalLottoCount(money);
	}

	public int calculateTotalLottoCount(Money money) {
		return money.getMoney() / Money.LOTTO_PRICE;
	}

	public int getManualLottoCount() {
		return manualLottoCount;
	}

	public int getAutoLottoCount() {
		return autoLottoCount;
	}
}
