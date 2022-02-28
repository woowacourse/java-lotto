package domain;

import static constant.LottoConstant.*;

public class LottoCount {

	private final int manualCount;
	private final int autoCount;

	private LottoCount(int manualCount, Money money) {
		validateCountRange(manualCount);
		validateCount(manualCount, money);
		this.manualCount = manualCount;

		Money consume = money.consume(manualCount * LOTTO_TICKET_PRICE);
		this.autoCount = consume.getPurchasableNumber(LOTTO_TICKET_PRICE);
	}

	public static LottoCount of(int manualCount, Money money) {
		return new LottoCount(manualCount, money);
	}

	private void validateCountRange(int count) {
		if (count < 0) {
			throw new IllegalArgumentException(INVALID_MANUAL_COUNT);
		}
	}

	private void validateCount(int count, Money money) {
		if (!money.isPurchasable(count * LOTTO_TICKET_PRICE)) {
			throw new IllegalArgumentException(INVALID_MANUAL_COUNT_WITH_MONEY);
		}
	}

	public int getManualCount() {
		return manualCount;
	}

	public int getAutoCount() {
		return autoCount;
	}
}
