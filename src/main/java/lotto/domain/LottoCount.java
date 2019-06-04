package lotto.domain;

import lotto.exception.InvalidPurchaseInformationException;

public class LottoCount {
    private static final int ZERO = 0;

    private final int autoLottoCount;
    private final int manualLottoCount;

    public LottoCount(int manualLottoCount, Money money) {
        if (manualLottoCount < ZERO) {
            throw new InvalidPurchaseInformationException("수동 로또의 개수는 0이상이여야 합니다.");
        }
        if (!money.canBuy(manualLottoCount)) {
            throw new InvalidPurchaseInformationException("구입 금액이 부족합니다.");
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
