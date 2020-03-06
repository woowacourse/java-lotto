package lotto.domain.purchase;

import java.util.Objects;

public class PurchasingCount {

	private static final int PURCHASING_UNIT = 1;

	private int purchasingCount;

	public PurchasingCount(int purchasingCount) {
		validate(purchasingCount);
		this.purchasingCount = purchasingCount;
	}

	private void validate(int purchasingCount) {
		if (purchasingCount < 0L) {
			throw new InvalidPurchasingCountException(InvalidPurchasingCountException.INVALID);
		}
	}

	public static PurchasingCount valueOf(String inputPurchasingCount) {
		return new PurchasingCount(parseToInt(inputPurchasingCount));
	}

	private static int parseToInt(String inputPurchasingCount) {
		try {
			return Integer.parseInt(inputPurchasingCount);
		} catch (NumberFormatException e) {
			throw new InvalidPurchasingCountException(InvalidPurchasingCountException.NOT_INTEGER);
		}
	}

	public PurchasingCount subtract(PurchasingCount manualPurchasingCount) {
		if (isImpossible(manualPurchasingCount)) {
			throw new InvalidPurchasingCountException(InvalidPurchasingCountException.MANUAL_OVER);
		}
		return new PurchasingCount(this.purchasingCount - manualPurchasingCount.purchasingCount);
	}

	private boolean isImpossible(PurchasingCount manualPurchasingCount) {
		return this.purchasingCount < manualPurchasingCount.purchasingCount;
	}

	public void buyLottoTicket() {
		purchasingCount = purchasingCount - PURCHASING_UNIT;
		validate(purchasingCount);
	}

	public boolean isAvailableForPurchase() {
		return purchasingCount > 0;
	}

	public int getPurchasingCount() {
		return purchasingCount;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		PurchasingCount that = (PurchasingCount)object;
		return purchasingCount == that.purchasingCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(purchasingCount);
	}

	@Override
	public String toString() {
		return Integer.toString(purchasingCount);
	}
}
