package lotto.domain.purchase;

public class TotalPurchasingCount {

	public static final int LOTTO_PRICE = 1_000;

	private final PurchasingCount manualPurchasingCount;
	private final PurchasingCount autoPurchasingCount;

	private TotalPurchasingCount(PurchasingCount manualPurchasingCount, PurchasingCount autoPurchasingCount) {
		this.manualPurchasingCount = manualPurchasingCount;
		this.autoPurchasingCount = autoPurchasingCount;
	}

	public static TotalPurchasingCount from(String inputManualPurchasingCount, LottoMoney lottoMoney) {
		int totalPurchasableCount = lottoMoney.calculatePurchasableCountBy(LOTTO_PRICE);
		PurchasingCount manualPurchasingCount = PurchasingCount.valueOf(inputManualPurchasingCount);

		int autoPurchasableCount = totalPurchasableCount - manualPurchasingCount.getPurchasingCount();
		PurchasingCount autoPurchasingCount = new PurchasingCount(autoPurchasableCount);
		return new TotalPurchasingCount(manualPurchasingCount, autoPurchasingCount);
	}

	public PurchasingCount getManualPurchasingCount() {
		return manualPurchasingCount;
	}

	public PurchasingCount getAutoPurchasingCount() {
		return autoPurchasingCount;
	}
}
