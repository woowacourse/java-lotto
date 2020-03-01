package lotto.domain.purchase;

import java.util.Iterator;
import java.util.Objects;

public class PurchasingCount implements Iterator<PurchasingCount> {

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

	public boolean isOverBy(int comparePurchasingCount) {
		return purchasingCount < comparePurchasingCount;
	}

	public void purchaseFor(ManualLottoTicketCount manualLottoTicketCount) {
		purchasingCount -= manualLottoTicketCount.getManualLottoTicketCount();
	}

	public int getPurchasingCount() {
		return purchasingCount;
	}

	@Override
	public boolean hasNext() {
		return purchasingCount > 0;
	}

	@Override
	public PurchasingCount next() {
		purchasingCount--;
		return this;
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
