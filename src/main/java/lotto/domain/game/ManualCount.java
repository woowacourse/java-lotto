package lotto.domain.game;

import lotto.domain.exceptions.ManualCountBoundException;
import lotto.utils.InputParser;

public class ManualCount {
    private static final String MANUAL_COUNT_BOUND = "구매 금액을 초과한 수동 구매는 불가능 합니다.";

    private final int count;

    private ManualCount(int manualCount, Count totalCount) {
        validBound(manualCount, totalCount);
        this.count = manualCount;
    }

    public static ManualCount is(int manualCount, Count totalCount) {
        return new ManualCount(manualCount, totalCount);
    }

    private void validBound(int manualCount, Count totalCount) {
        if (0 > manualCount || totalCount.getCount() < manualCount) {
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
