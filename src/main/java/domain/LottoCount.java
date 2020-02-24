package domain;

public class LottoCount {
    private final int lottoCount;

    public LottoCount(final int lottoCount) {
        this.lottoCount = lottoCount;
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public int getAutoLottoCount(final ManualCount manualCount) {
        return lottoCount - manualCount.getManualCount();
    }
}
