package domain.player;

import utils.ExceptionMessage;

public class LottoCount {

    private static final int ZERO = 0;

    private final int manualLottoCount;
    private final int autoLottoCount;

    public LottoCount(int manualLottoCount, int autoLottoCount) {
        validateNegativeCount(manualLottoCount);
        validateManualLottoCountExcessTotalLottoCount(autoLottoCount);
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = autoLottoCount;
    }

    private void validateNegativeCount(int manualLottoCount) {
        if (manualLottoCount < ZERO) {
            throw new IllegalArgumentException(ExceptionMessage.MANUAL_LOTTO_COUNT_IS_NOT_UNDER_ZERO);
        }
    }

    private void validateManualLottoCountExcessTotalLottoCount(int autoLottoCount) {
        if (autoLottoCount < ZERO) {
            throw new IllegalArgumentException(ExceptionMessage.MANUAL_LOTTO_PURCHASE_MONEY_EXCESS_TOTAL_MONEY);
        }
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }
}
