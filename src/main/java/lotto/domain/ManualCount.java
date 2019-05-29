package lotto.domain;

import lotto.domain.exceptions.ManualCountBoundException;

public class ManualCount {
    private int manualCount;

    private ManualCount(int manualCount, TotalCount totalCount) {
        if (totalCount.getTotalCount() < manualCount) {
            throw new ManualCountBoundException("구매 금액을 초과한 수동 구매는 불가능 합니다.");
        }
        this.manualCount = manualCount;
    }

    public static ManualCount is(int manualCount, TotalCount totalCount) {
        return new ManualCount(manualCount, totalCount);
    }

    public int getManualCount() {
        return manualCount;
    }

    public AutoCount getAutoCount(TotalCount totalCount) {
        return new AutoCount(totalCount.getTotalCount() - this.manualCount);
    }
}
