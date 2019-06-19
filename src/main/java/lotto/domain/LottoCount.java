package lotto.domain;

import lotto.exception.InvalidPurchaseInformationException;

public class LottoCount {
    private static final int ZERO = 0;

    private final int autoLottoCount;
    private final int manualLottoCount;

    public LottoCount(int manualLottoCount, Money money) {
        if (manualLottoCount < ZERO) {
            throw new InvalidPurchaseInformationException(
                    InvalidPurchaseInformationException.LOTTO_COUNT_BIGGER_THAN_ZERO_MSG);
        }
        if (!money.canBuy(manualLottoCount)) {
            throw new InvalidPurchaseInformationException(InvalidPurchaseInformationException.NOT_ENOUGH_MONEY_MSG);
        }
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = money.calculateMaxNumberOfPurchase() - manualLottoCount;
    }

    int getAutoLottoCount() {
        return autoLottoCount;
    }

    int getManualLottoCount() {
        return manualLottoCount;
    }

    boolean hasManualLottos() {
        return manualLottoCount > 0;
    }

    boolean isEqualManualLottoCount(int count) {
        return this.manualLottoCount == count;
    }
}
