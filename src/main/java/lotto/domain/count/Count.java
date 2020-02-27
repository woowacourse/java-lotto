package lotto.domain.count;

public class Count {

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
            throw CountException.manualCountOverTotal();
        }
        if (totalCount < MINIMUM_COUNT) {
            throw CountException.negativeTotalCount();
        }
        if (manualCount < MINIMUM_COUNT) {
            throw CountException.negativeManualCount();
        }
    }

    public int getManualCounts() {
        return manualCount;
    }

    public int getAutoCounts() {
        return totalCount - manualCount;
    }
}
