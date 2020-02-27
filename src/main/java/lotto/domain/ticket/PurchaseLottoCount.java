package lotto.domain.ticket;

import java.util.Objects;

public final class PurchaseLottoCount {
	private final LottoCount totalPurchaseCount;
	private final LottoCount manualPurchaseCount;

	public PurchaseLottoCount(LottoCount totalPurchaseCount, LottoCount manualPurchaseCount) {
		validate(totalPurchaseCount, manualPurchaseCount);
		this.totalPurchaseCount = totalPurchaseCount;
		this.manualPurchaseCount = manualPurchaseCount;
	}

	private void validate(LottoCount totalPurchaseCount, LottoCount manualPurchaseCount) {
		validateNull(totalPurchaseCount, manualPurchaseCount);
		validateCountSize(totalPurchaseCount, manualPurchaseCount);
	}

	private void validateNull(LottoCount totalPurchaseCount, LottoCount manualPurchaseCount) {
		if (Objects.isNull(totalPurchaseCount) || Objects.isNull(manualPurchaseCount)) {
			throw new NullPointerException();
		}
	}

	private void validateCountSize(LottoCount totalPurchaseCount, LottoCount manualPurchaseCount) {
		if (totalPurchaseCount.isLessThan(manualPurchaseCount)) {
			throw new IllegalArgumentException();
		}
	}

	public LottoCount calculateAutoCount() {
		return totalPurchaseCount.minus(manualPurchaseCount);
	}

	public LottoCount getManualCount() {
		return manualPurchaseCount;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		PurchaseLottoCount that = (PurchaseLottoCount)object;
		return Objects.equals(this.totalPurchaseCount, that.totalPurchaseCount) &&
			Objects.equals(this.manualPurchaseCount, that.manualPurchaseCount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(totalPurchaseCount, manualPurchaseCount);
	}
}
