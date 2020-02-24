package domain;

public class LottoCount {
    private final int lottoCount;

    public LottoCount(int lottoCount) {
        this.lottoCount = lottoCount;
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public int getAutoLottoCount(ManualCount manualCount) {
        return lottoCount - manualCount.getManualCount();
    }
}
