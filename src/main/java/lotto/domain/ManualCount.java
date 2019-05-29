package lotto.domain;

import lotto.domain.exceptions.ManualCountBoundException;

public class ManualCount {
    private static final String MANUAL_COUNT_BOUND = "구매 금액을 초과한 수동 구매는 불가능 합니다.";

    private final int count;

    private ManualCount(int count, TotalCount totalCount) {
        validBound(count, totalCount);
        this.count = count;
    }

    public static ManualCount is(int count, TotalCount totalCount) {
        return new ManualCount(count, totalCount);
    }

    private void validBound(int count, TotalCount totalCount) {
        if (totalCount.getTotalCount() < count) {
            throw new ManualCountBoundException(MANUAL_COUNT_BOUND);
        }
    }

    public int getCount() {
        return count;
    }

    public AutoCount getAutoCount(TotalCount totalCount) {
        return new AutoCount(totalCount.getTotalCount() - this.count);
    }

    public boolean isZero() {
        return count == 0;
    }
}
