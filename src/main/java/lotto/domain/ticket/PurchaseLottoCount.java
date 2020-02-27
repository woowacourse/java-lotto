package lotto.domain.ticket;

import java.util.Objects;

public final class PurchaseLottoCount {
	private static final String NULL_REFERENCE_EXCEPTION_MESSAGE = "객체에 NULL이 허용되지 않습니다.";
	private static final String TOTAL_COUNT_LESS_THAN_MANUAL_COUNT_EXCEPTION_MESSAGE = "수동 발매 수량이 전체 발매수량보다 클 수 없습니다.";

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
			throw new NullPointerException(NULL_REFERENCE_EXCEPTION_MESSAGE);
		}
	}

	private void validateCountSize(LottoCount totalPurchaseCount, LottoCount manualPurchaseCount) {
		if (totalPurchaseCount.isLessThan(manualPurchaseCount)) {
			throw new IllegalArgumentException(TOTAL_COUNT_LESS_THAN_MANUAL_COUNT_EXCEPTION_MESSAGE);
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
