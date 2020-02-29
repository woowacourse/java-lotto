package lotto.domain;

import lotto.domain.exception.InvalidLottoPurchaseMoneyException;
import lotto.domain.exception.NotEnoughMoneyException;

public class LottoPurchaseMoney {
	private static final int LOTTO_PRICE = 1_000;

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

	public LottoBuyCount getBuyCount(String manual)
	{
		int totalCount = lottoPurchaseMoney / LOTTO_PRICE;
		int manualCount = Integer.parseInt(manual);
		if (manualCount > totalCount) {
			throw new NotEnoughMoneyException();
		}
		return new LottoBuyCount(manualCount, totalCount - manualCount);
	}

	public int getValue() {
		return lottoPurchaseMoney;
	}
}
