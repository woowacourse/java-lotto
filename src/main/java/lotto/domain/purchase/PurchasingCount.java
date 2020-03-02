package lotto.domain.purchase;

import java.util.Objects;

public class PurchasingCount {

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
