package lotto.domain.count;

public class Count {

    public static final int MINIMUM_COUNT = 0;
    private final int totalCount;
    private final int manualCount;

    public Count(int totalCount, int manualCount) {
        validate(totalCount, manualCount);
        this.totalCount = totalCount;
        this.manualCount = manualCount;
    }

    private void validate(int totalCount, int manualCount) {
        if (totalCount < manualCount) {
            throw new IllegalArgumentException("총 구매 개수는 수동 구매 개수보다 작을 수 없습니다.");
        }
        if (totalCount < MINIMUM_COUNT) {
            throw new IllegalArgumentException("총 구매 개수는 음수일 수 없습니다.");
        }
        if (manualCount < MINIMUM_COUNT) {
            throw new IllegalArgumentException("수동 구매 개수는 음수일 수 없습니다.");
        }
    }

    public int getManualCounts() {
        return manualCount;
    }

    public int getAutoCounts() {
        return totalCount - manualCount;
    }
}
