package domain;

import java.util.List;

import utils.LotteryMessage;

public class PurchaseInformation {

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
			throw new IllegalArgumentException(LotteryMessage.MANUAL_PURCHASE_IS_LARGER_THAN_PURCHASE_AMOUNT);
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

	public double calculateEarningRate(final int earningAmount) {
		return purchaseAmount.calculateEarningRate(earningAmount);
	}

}
