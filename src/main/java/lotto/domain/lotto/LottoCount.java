package lotto.domain.lotto;

public class LottoCount {
    private final int manualCount;
    private final int autoCount;

    public LottoCount(int manualCount, int totalCount) {
        validateLottoCount(manualCount, totalCount);
        this.manualCount = manualCount;
        this.autoCount = totalCount - this.manualCount;
    }

    private void validateLottoCount(int manualCount, int totalCount) {
        if (manualCount > totalCount || manualCount < 0) {
            throw new IllegalArgumentException("[ERROR] 구매 가능한 로또 개수 범위가 아닙니다.");
        }
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }
}
