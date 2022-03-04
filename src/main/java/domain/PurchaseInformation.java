package domain;

import java.util.List;

public class PurchaseInformation {

	public static final String MANUAL_PURCHASE = "수동 구입";

	private static final String MANUAL_PURCHASE_IS_LARGER_THAN_PURCHASE_AMOUNT = MANUAL_PURCHASE + " 갯수가 "
		+ PurchaseAmount.PURCHASE_AMOUNT + "보다 많습니다";

	private final PurchaseAmount purchaseAmount;
	private final Lotteries manualLotteries;
	private final int theNumberOfManualLotteries;

	public PurchaseInformation(final PurchaseAmount purchaseAmount, final Lotteries manualLotteries,
			final int theNumberOfManualLotteries) {
		validateTheNumberOfLotteries(purchaseAmount, theNumberOfManualLotteries);
		this.purchaseAmount = purchaseAmount;
		this.manualLotteries = manualLotteries;
		this.theNumberOfManualLotteries = theNumberOfManualLotteries;
	}

	private void validateTheNumberOfLotteries(final PurchaseAmount purchaseAmount,
			final int theNumberOfManualLotteries) {
		if (!hasEnoughMoney(purchaseAmount, theNumberOfManualLotteries)) {
			throw new IllegalArgumentException(MANUAL_PURCHASE_IS_LARGER_THAN_PURCHASE_AMOUNT);
		}
	}

	private boolean hasEnoughMoney(final PurchaseAmount purchaseAmount, final int theNumberOfManualLotteries) {
		return purchaseAmount.getTheNumberOfPurchasedLotteries() >= theNumberOfManualLotteries;
	}

	public int getTheNumberOfAutoPurchasedLotteries() {
		return purchaseAmount.getTheNumberOfPurchasedLotteries() - theNumberOfManualLotteries;
	}

	public List<Lottery> getManualLotteries() {
		return manualLotteries.getLotteries();
	}

	public double calculateEarningRate(final long earningAmount) {
		return purchaseAmount.calculateEarningRate(earningAmount);
	}

}
