package lotto.domain;

import java.util.Objects;

public class TotalCount {
	private final LottoCount totalCount;
	private final LottoCount manualCount;

	public TotalCount(LottoCount totalCount, LottoCount manualCount) {
		validate(totalCount, manualCount);
		this.totalCount = totalCount;
		this.manualCount = manualCount;
	}

	private void validate(LottoCount total, LottoCount manualCount) {
		validateNullCount(total, manualCount);
		validateManualCount(total, manualCount);
	}

	private void validateNullCount(LottoCount total, LottoCount manualCount) {
		if (Objects.isNull(total) || Objects.isNull(manualCount)) {
			throw new NullPointerException();
		}
	}

	private void validateManualCount(LottoCount total, LottoCount manualCount) {
		if (manualCount.isBiggerThan(total)) {
			throw new IllegalArgumentException();
		}
	}

	public LottoCount calculateAutoCount() {
		return totalCount.minus(manualCount);
	}
}
