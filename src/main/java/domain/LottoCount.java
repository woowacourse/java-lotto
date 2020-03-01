package domain;

public class LottoCount {
    private static final int MIN_AUTO_LOTTO_COUNT = 0;
    public static final int MIN_MANUAL_LOTTO_COUNT = 0;

    private int autoLottoCount;
    private int manualLottoCount;

    public LottoCount(int totalLottoCount, int manualLottoCount) {
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = totalLottoCount - this.manualLottoCount;
        checkOverAmountManualLottoCount();
        checkNegativeNumber();
    }

    private void checkOverAmountManualLottoCount() {
        if (this.autoLottoCount < MIN_AUTO_LOTTO_COUNT) {
            throw new IllegalArgumentException("수동 로또 개수가 전체 로또 개수보다 클 수 없습니다.");
        }
    }

    private void checkNegativeNumber() {
        if (this.manualLottoCount < MIN_MANUAL_LOTTO_COUNT) {
            throw new IllegalArgumentException("수동 로또 개수는 음수일 수 없습니다.");
        }
    }

    public int getManualCount() {
        return this.manualLottoCount;
    }

    public int getAutoCount() {
        return this.autoLottoCount;
    }
}
