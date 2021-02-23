package lotto.domain.lotto;

public class LottoCount {
    private final int manualLottoCount;
    private final int autoLottoCount;

    public LottoCount(int manualCount, int totalCount) {
        validateLottoCount(manualCount, totalCount);
        this.manualLottoCount = manualCount;
        this.autoLottoCount = totalCount - manualLottoCount;
    }

    private void validateLottoCount(int manualCount, int totalCount) {
        if (manualCount > totalCount || manualCount < 0) {
            throw new IllegalArgumentException("[ERROR] 구매 가능한 로또 개수 범위가 아닙니다.");
        }
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }
}
