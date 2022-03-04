package domain;

public class UserCount {

    private static final String MAXIMUM_MANUAL_COUNT_ERROR_MESSAGE = "[ERROR] 구매 가능 수량을 초과하여 입력할 수 없습니다.";
    private static final String MANUAL_COUNT_ONLY_NATURAL_ERROR_MESSAGE = "[ERROR] 수동 구매 수량에 음수를 입력할 수 없습니다.";
    private static final int NATURAL = 0;

    private final int manualCount;
    private final int autoCount;

    public UserCount(int totalCount, int manualCount) {
        validateCountOnlyNaturalNumber(manualCount);
        validateMaximumManualCount(totalCount, manualCount);
        this.manualCount = manualCount;
        this.autoCount = totalCount - manualCount;
    }

    private void validateCountOnlyNaturalNumber(int manualCount) {
        if (manualCount < NATURAL) {
            throw new IllegalArgumentException(MANUAL_COUNT_ONLY_NATURAL_ERROR_MESSAGE);
        }
    }

    private void validateMaximumManualCount(int totalCount, int manualCount) {
        if (totalCount < manualCount) {
            throw new IllegalArgumentException(MAXIMUM_MANUAL_COUNT_ERROR_MESSAGE);
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }
}
