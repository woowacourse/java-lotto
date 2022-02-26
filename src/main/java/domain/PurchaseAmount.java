package domain;

import utils.LotteryMessage;

public class PurchaseAmount {

	private static final int AMOUNT_MIN_RANGE = 1_000;
	private static final int AMOUNT_MAX_RANGE = 100_000;
	private final int purchaseAmount;

	public PurchaseAmount(final int purchaseAmount) {
		validateRange(purchaseAmount);
		this.purchaseAmount = purchaseAmount;
	}

	private void validateRange(final int number) {
		if (number < AMOUNT_MIN_RANGE || number > AMOUNT_MAX_RANGE) {
			throw new IllegalArgumentException(LotteryMessage.PURCHASE_AMOUNT_RANGE_ERROR);
		}
	}

	public int getPurchasedLotteries() {
		return purchaseAmount / AMOUNT_MIN_RANGE;
	}

	public double calculateEarningRate(final int earningAmount) {
		return (double)earningAmount / purchaseAmount;
	}

}
