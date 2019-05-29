package lotto.domain.game;

import lotto.domain.exceptions.ManualCountBoundException;

public class ManualCount {
    private static final String MANUAL_COUNT_BOUND = "구매 금액을 초과한 수동 구매는 불가능 합니다.";

    private final int count;

    private ManualCount(int count, Count totalCount) {
        validBound(count, totalCount);
        this.count = count;
    }

    public static ManualCount is(int count, Count totalCount) {
        return new ManualCount(count, totalCount);
    }

    private void validBound(int count, Count totalCount) {
        if (totalCount.getCount() < count) {
            throw new ManualCountBoundException(MANUAL_COUNT_BOUND);
        }
    }

    public int getCount() {
        return count;
    }

    public Count getAutoCount(Count count) {
        return new Count(count.getCount() - this.count);
    }

    public boolean isZero() {
        return count == 0;
    }
}
