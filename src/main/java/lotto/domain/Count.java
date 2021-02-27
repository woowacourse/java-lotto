package lotto.domain;

public class Count {

    public static final String NUMBER_EXCEED_ERROR = "[ERROR] 구매 가능한 수를 초과했습니다.";
    private final int totalCount;
    private int manualCount;
    private int autoCount;

    public Count(int count) {
        this.totalCount = count;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void manualCount(int count) {
        validateManualCount(count);
        this.manualCount = count;
        this.autoCount = totalCount - count;
    }

    public int getManualCount() {
        return this.manualCount;
    }

    public int getAutoCount() {
        return this.autoCount;
    }

    private void validateManualCount(int count) {
        if (count > totalCount) {
            throw new IllegalArgumentException(NUMBER_EXCEED_ERROR);
        }
    }
}
