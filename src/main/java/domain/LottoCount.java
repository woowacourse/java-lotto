package domain;

public class LottoCount {
    private static final int MIN_AUTO_LOTTO_COUNT = 0;

    private int autoLottoCount;
    private int manualLottoCount;

    public LottoCount(int totalLottoCount, String manualLottoCount) {
        this.manualLottoCount = checkNotNumber(manualLottoCount);
        this.autoLottoCount = totalLottoCount - this.manualLottoCount;
        checkOverAmountManualLottoCount();
    }

    private void checkOverAmountManualLottoCount() {
        if (this.autoLottoCount < MIN_AUTO_LOTTO_COUNT) {
            throw new IllegalArgumentException("수동 로또 개수가 전체 로또 개수보다 클 수 없습니다.");
        }
    }

    private int checkNotNumber(String manualLottoCount) {
        try {
            return Integer.parseInt(manualLottoCount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("수동 로또 개수는 숫자로 입력해야합니다.");
        }
    }

    public int getManualCount() {
        return this.manualLottoCount;
    }
}
