package lotto.domain;

import lotto.exceptions.ManualCountBoundException;

public class ManualCount {
    private int manualCount;

    private ManualCount(String input, TotalCount totalCount) {
        int manualCount = Integer.parseInt(input);
        if (totalCount.getTotalCount() < manualCount) {
            throw new ManualCountBoundException("구매 금액을 초과한 수동 구매는 불가능 합니다.");
        }
        this.manualCount = manualCount;
    }

    public static ManualCount is(String input, TotalCount totalCount) {
        return new ManualCount(input, totalCount);
    }

    public AutoCount getAutoCount(TotalCount totalCount) {
        return new AutoCount(totalCount.getTotalCount() - this.manualCount);
    }
}
