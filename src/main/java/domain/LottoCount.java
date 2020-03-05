package domain;

public class LottoCount {
    private static final int NEGATIVE_CRITERIA_POINT = 0;
    private int manualCount;
    private int autoCount;

    public LottoCount(final int lottoCount, final int manualCount) {
        this.manualCount = manualCount;
        checkManualAvailableRange(lottoCount);
        this.autoCount = lottoCount - manualCount;
    }

    private void checkManualAvailableRange(final int lottoCount) {
        if (isManualAvailableRange(lottoCount)) {
            throw new IllegalArgumentException(String.format("수동으로 구매 가능한 로또 개수가 아닙니다. 현재 입력 : %s ", manualCount));
        }
    }

    private boolean isManualAvailableRange(final int lottoCount) {
        return manualCount < NEGATIVE_CRITERIA_POINT || manualCount > lottoCount;
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
