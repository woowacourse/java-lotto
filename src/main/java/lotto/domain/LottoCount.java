package lotto.domain;

public class LottoCount {
	public static final int LOTTO_PRICE = 1000;
	private static final int MINIMUM_MONEY = 1000;
	private static final int MINIMUM_MANUAL_LOTTO_COUNT = 0;

	private final int manualLottoCount;
	private final int autoLottoCount;

	public LottoCount(int money, int manualLottoCount) {
		validate(money, manualLottoCount);

		this.manualLottoCount = manualLottoCount;
		this.autoLottoCount = calculateTotalLottoCount(money) - this.manualLottoCount;
	}

	public LottoCount(int money) {
		this(money, MINIMUM_MANUAL_LOTTO_COUNT);
	}

	private void validate(int money, int manualLottoCount) {
		validateLackOf(money);
		validateMoneyUnit(money);
		validateRangeOfManualLottoCount(money, manualLottoCount);
	}

	private void validateLackOf(int money) {
		if (money < MINIMUM_MONEY) {
			throw new IllegalArgumentException("돈이 부족합니다.");
		}
	}

	private void validateMoneyUnit(int money) {
		if (money % LOTTO_PRICE != 0) {
			throw new IllegalArgumentException("천 원 단위로 입력하셔야 합니다.");
		}
	}

	private void validateRangeOfManualLottoCount(int money, int manualLottoCount) {
		if (isInvalidRange(money, manualLottoCount)) {
			throw new IllegalArgumentException("구매할 수 있는 수동 로또 범위가 아닙니다!");
		}
	}

	private boolean isInvalidRange(int money, int manualLottoCount) {
		return manualLottoCount < MINIMUM_MANUAL_LOTTO_COUNT || manualLottoCount > calculateTotalLottoCount(money);
	}

	public int calculateTotalLottoCount(int money) {
		return money / LOTTO_PRICE;
	}

	public int getManualLottoCount() {
		return manualLottoCount;
	}

	public int getAutoLottoCount() {
		return autoLottoCount;
	}
}
