package lotto.domain.game;

import lotto.exceptions.CountRangeException;

public class ManualCount {
    private static final int COUNT_LOWER_BOUND = 0;
    private static final int EQUAL_OR_UNDER = 0;

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
            throw new CountRangeException();
        }
    }

    private boolean isValidRange(int manualCount, Count totalCount) {
        return COUNT_LOWER_BOUND <= manualCount && EQUAL_OR_UNDER <= totalCount.compareTo(manualCount);
    }

    public Count getAutoCount(Count count) {
        return new Count(count.subtract(this.count));
    }

    public boolean isZero() {
        return count == COUNT_LOWER_BOUND;
    }

    public boolean isUnder(int indicator) {
        return indicator <= count;
    }
}
