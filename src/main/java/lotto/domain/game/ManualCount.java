package lotto.domain.game;

import lotto.domain.exceptions.ManualCountBoundException;

public class ManualCount {
    private static final String MANUAL_COUNT_RANGE = "수동 구매 가능 범위를 벗어났습니다.";

    private final int count;

    private ManualCount(int manualCount, Count totalCount) {
        validBound(manualCount, totalCount);
        this.count = manualCount;
    }

    public static ManualCount is(int manualCount, Count totalCount) {
        return new ManualCount(manualCount, totalCount);
    }

    private void validBound(int manualCount, Count totalCount) {
        if (!isValidRange(manualCount, totalCount)) {
            throw new ManualCountBoundException(MANUAL_COUNT_RANGE);
        }
    }

    private boolean isValidRange(int manualCount, Count totalCount) {
        return 0 <= manualCount && 0 <= totalCount.compareTo(manualCount);
    }

    public int getCount() {
        return count;
    }

    public Count getAutoCount(Count count) {
        return new Count(count.subtract(this.count));
    }

    public boolean isZero() {
        return count == 0;
    }

    public boolean isUnder(int indicator) {
        return indicator <= count;
    }
}
