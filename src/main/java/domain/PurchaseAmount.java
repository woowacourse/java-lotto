package domain;

public class PurchaseAmount {

	public static final String MONEY_UNIT = "원";
	public static final String PURCHASE_AMOUNT = "구입 금액";

	private static final int AMOUNT_MIN_RANGE = 1_000;
	private static final int AMOUNT_MAX_RANGE = 100_000;
	private static final String PURCHASE_AMOUNT_RANGE_ERROR = PURCHASE_AMOUNT + "의 범위는 "
		+ AMOUNT_MIN_RANGE + MONEY_UNIT + "~" + AMOUNT_MAX_RANGE + MONEY_UNIT + " 입니다.";

	private final int purchaseAmount;

	public PurchaseAmount(final int purchaseAmount) {
		validateRange(purchaseAmount);
		this.purchaseAmount = purchaseAmount;
	}

	private void validateRange(final int number) {
		if (isOutOfRange(number)) {
			throw new IllegalArgumentException(PURCHASE_AMOUNT_RANGE_ERROR);
		}
	}

	private boolean isOutOfRange(final int number) {
		return number < AMOUNT_MIN_RANGE || number > AMOUNT_MAX_RANGE;
	}

	public int getTheNumberOfPurchasedLotteries() {
		return purchaseAmount / AMOUNT_MIN_RANGE;
	}

	public double calculateEarningRate(final long earningAmount) {
		return (double) earningAmount / purchaseAmount;
	}

}
