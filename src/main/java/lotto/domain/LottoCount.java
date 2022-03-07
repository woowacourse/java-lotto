package lotto.domain;

import lotto.exception.LottoCountException;

public class LottoCount {

    private final int manualLottoCount;
    private final int autoLottoCount;

    public LottoCount(int totalLottoCount, int manualLottoCount) {
        checkNegative(manualLottoCount);
        checkLottoCountOverMoney(totalLottoCount, manualLottoCount);
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = totalLottoCount - manualLottoCount;
    }

    private void checkNegative(int manualLottoCount) {
        if (isNegativeNumber(manualLottoCount)) {
            throw new LottoCountException(LottoCountException.LOTTO_COUNT_NOT_NEGATIVE_NUMBER_ERROR);
        }
    }

    private boolean isNegativeNumber(int manualLottoCount) {
        return manualLottoCount < 0;
    }

    private void checkLottoCountOverMoney(int totalLottoCount, int lottoCount) {
        if (isLottoCountOverMoney(totalLottoCount, lottoCount)) {
            throw new LottoCountException(LottoCountException.LOTTO_COUNT_OVER_MONEY_ERROR);
        }
    }

    private boolean isLottoCountOverMoney(int totalLottoCount, int lottoCount) {
        return totalLottoCount < lottoCount;
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }
}
