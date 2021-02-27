package lotto.domain;

public class Count {

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
        this.manualCount = count;
        this.autoCount = totalCount - count;
    }

    public int getManualCount() {
        return this.manualCount;
    }

    public int getAutoCount() {
        return this.autoCount;
    }
}
