package lotto.model;

public class LottoCount {

    private final int lottoCount;
    private final int manualLottoCount;
    private final int autoLottoCount;

    public LottoCount(final int manualLottoCount, final Money money) {
        validateLottoCount(manualLottoCount, money);
        this.lottoCount = money.lottoCount();
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = money.lottoCount() - manualLottoCount;
    }

    private void validateLottoCount(final int manualLottoCount, final Money money) {
        if (manualLottoCount > money.lottoCount()) {
            throw new IllegalArgumentException(LottoMachine.BUYING_LOTTO_COUNT_EXCESS_ERROR_MESSAGE);
        }
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }
}
