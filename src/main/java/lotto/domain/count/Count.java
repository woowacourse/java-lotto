package lotto.domain.count;

public class Count {

    private static final String MANUAL_COUNT_OVER_TOTAL_COUNT = "총 구매 개수는 수동 구매 개수보다 작을 수 없습니다.";
    private static final String NEGATIVE_TOTAL_COUNT = "총 구매 개수는 음수일 수 없습니다.";
    private static final String NEGATIVE_MANUAL_COUNT = "수동 구매 개수는 음수일 수 없습니다.";
    private static final int MINIMUM_COUNT = 0;

    private final int totalCount;
    private final int manualCount;

    public Count(int totalCount, int manualCount) {
        validate(totalCount, manualCount);
        this.totalCount = totalCount;
        this.manualCount = manualCount;
    }

    private void validate(int totalCount, int manualCount) {
        if (totalCount < manualCount) {
            throw new IllegalArgumentException(MANUAL_COUNT_OVER_TOTAL_COUNT);
        }
        if (totalCount < MINIMUM_COUNT) {
            throw new IllegalArgumentException(NEGATIVE_TOTAL_COUNT);
        }
        if (manualCount < MINIMUM_COUNT) {
            throw new IllegalArgumentException(NEGATIVE_MANUAL_COUNT);
        }
    }

    public int getManualCounts() {
        return manualCount;
    }

    public int getAutoCounts() {
        return totalCount - manualCount;
    }
}
