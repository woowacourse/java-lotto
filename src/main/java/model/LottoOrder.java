package model;

public class LottoOrder {
    static final String TOO_MANY_MANUAL = "[ERROR] 수동 로또 구매 수는 전체 구매 수를 넘을 수 없습니다.";

    private final int manualLottoCount;
    private final int autoLottoCount;

    public LottoOrder(LottoPurchasingMoney lottoPurchasingMoney, int manualLottoCount) {
        validateManualCount(lottoPurchasingMoney, manualLottoCount);
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = lottoPurchasingMoney.getPurchasableCount() - manualLottoCount;
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }

    private void validateManualCount(LottoPurchasingMoney lottoPurchasingMoney, int manualLottoCount) {
        if (lottoPurchasingMoney.getPurchasableCount() < manualLottoCount) {
            throw new IllegalArgumentException(TOO_MANY_MANUAL);
        }
    }
}
