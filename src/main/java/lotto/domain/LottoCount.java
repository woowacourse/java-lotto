package lotto.domain;

public class LottoCount {
    private static final int MINIMUM_COUNT = 0;

    private final int manualCount;
    private final int automaticCount;

    public LottoCount(int totalCount, int manualCount) {
        checkRange(totalCount, manualCount);
        this.manualCount = manualCount;
        this.automaticCount = totalCount - manualCount;
    }

    private void checkRange(int totalCount, int manualCount) {
        if (manualCount < MINIMUM_COUNT) {
            throw new RuntimeException("수동으로 구매할 로또 수로 음수는 불가합니다.");
        }
        if (totalCount < manualCount) {
            throw new RuntimeException("수동으로 구매할 로또 수는 총 로또 수를 초과할 수 없습니다.");
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutomaticCount() {
        return automaticCount;
    }
}
