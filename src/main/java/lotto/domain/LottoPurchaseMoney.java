package lotto.domain;

import lotto.domain.exception.InvalidLottoPurchaseMoneyException;

public class LottoPurchaseMoney {
	private static final int LOTTO_PRICE = 1000;

	private final int lottoPurchaseMoney;

	public LottoPurchaseMoney(String lottoPurchaseMoney) {
		validate(lottoPurchaseMoney);
		this.lottoPurchaseMoney = Integer.parseInt(lottoPurchaseMoney);
	}

	private void validate(String lottoPurchaseMoney) {
		if (isNotNumber(lottoPurchaseMoney)) {
			throw new NumberFormatException("숫자가 아닌 값을 입력하면 안됩니다.");
		}
		if (canNotBought(lottoPurchaseMoney)) {
			throw new InvalidLottoPurchaseMoneyException();
		}
	}

	private boolean isNotNumber(String lottoPurchaseMoney) {
		try {
			Integer.parseInt(lottoPurchaseMoney);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

	private boolean canNotBought(String lottoPurchaseMoney) {
		return Integer.parseInt(lottoPurchaseMoney) < LOTTO_PRICE;
	}

	public int getBuyCount() {
		return lottoPurchaseMoney / LOTTO_PRICE;
	}

	public int getValue() {
		return lottoPurchaseMoney;
	}
}
