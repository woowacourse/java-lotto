package lotto.domain;

public class LottoPurchaseMoney {
	private static final int LOTTO_PURCHASE_UNIT = 1000;
	private static final String INVALID_PURCHASE_MONEY_MESSAGE = "잘못된 구입 금액을 입력하셨습니다.";

	private final int lottoPurchaseMoney;

	public LottoPurchaseMoney(int lottoPurchaseMoney) {
		validate(lottoPurchaseMoney);
		this.lottoPurchaseMoney = lottoPurchaseMoney;
	}

	private void validate(int lottoPurchaseMoney) {
		if (isUnitFit(lottoPurchaseMoney)) {
			throw new IllegalArgumentException(INVALID_PURCHASE_MONEY_MESSAGE);
		}
	}

	private boolean isUnitFit(int lottoPurchaseMoney) {
		return lottoPurchaseMoney % LOTTO_PURCHASE_UNIT != 0;
	}

	public int getBuyCount() {
		return lottoPurchaseMoney / LOTTO_PURCHASE_UNIT;
	}

	public int getValue() {
		return lottoPurchaseMoney;
	}
}
