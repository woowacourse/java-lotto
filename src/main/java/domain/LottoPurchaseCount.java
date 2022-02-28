package domain;

import java.util.Objects;

public class LottoPurchaseCount {

    private static final String ERROR_MESSAGE_MANUAL_COUNT = "수동으로 구매할 로또수는 음수일 수 없습니다.";

    private static final int MINIMUM_VALUE = 0;

    private final int manualCount;
    private final int automaticCount;

    public LottoPurchaseCount(int manualCount, int automaticCount) {
        validateManualRange(manualCount);
        this.manualCount = manualCount;
        this.automaticCount = automaticCount;
    }

    private void validateManualRange(int manualCount) {
        if (manualCount < MINIMUM_VALUE) {
            throw new IllegalArgumentException(ERROR_MESSAGE_MANUAL_COUNT);
        }
    }

    public int getAutomaticCount() {
        return automaticCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LottoPurchaseCount))
            return false;
        LottoPurchaseCount that = (LottoPurchaseCount)o;
        return manualCount == that.manualCount && automaticCount == that.automaticCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manualCount, automaticCount);
    }
}
