package domain;

public class LottoCount {
    private int autoCount;
    private int manualCount;

    public LottoCount(final int lottoCount, final int manualCount) {
        this.autoCount = lottoCount - manualCount;
        this.manualCount = manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getTotalCount() {
        return autoCount + manualCount;
    }
}
