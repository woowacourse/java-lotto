package domain;

public class UserCount {

    private static final String MANUAL_COUNT_ERROR_MESSAGE = "[ERROR] 구매 가능 수량을 초과하여 입력할 수 없습니다.";

    private final int totalCount;
    private final int manualCount;
    private final int autoCount;

    public UserCount(int totalCount, int manualCount) {
        validateManualCount(totalCount, manualCount);
        this.totalCount = totalCount;
        this.manualCount = manualCount;
        this.autoCount = totalCount - manualCount;
    }

    private void validateManualCount(int maxCount, int manualCount) {
        if (maxCount < manualCount) {
            throw new IllegalArgumentException(MANUAL_COUNT_ERROR_MESSAGE);
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }
}
