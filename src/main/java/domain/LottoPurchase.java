package domain;

public class LottoPurchase {
    private static final int MINIMUM_RANGE = 0;
    private static final String ERROR_NEGATIVE_NUMBER = "[ERROR] 양수만 입력해주세요.";

    private final int manualCount;
    private final int autoCount;

    public LottoPurchase(int manualCount, int autoCount) {
        validateManualCount(manualCount);
        this.manualCount = manualCount;
        this.autoCount = autoCount;
    }

    private void validateManualCount(int manualCount) {
        if (manualCount < MINIMUM_RANGE) {
            throw new IllegalArgumentException(ERROR_NEGATIVE_NUMBER);
        }
    }

    public int manual() {
        return this.manualCount;
    }

    public int auto() {
        return this.autoCount;
    }
}
