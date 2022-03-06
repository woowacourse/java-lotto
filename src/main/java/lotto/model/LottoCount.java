package lotto.model;

public class LottoCount {

    private static final String BUYING_LOTTO_COUNT_EXCESS_ERROR_MESSAGE = "[ERROR] 구매할 수 있는 수량을 넘었습니다.";

    private int lottoCount;
    private int manualLottoCount;
    private int autoLottoCount;

    public LottoCount(final int manualLottoCount, final Money money) {
        validateLottoCount(manualLottoCount, money);
        this.lottoCount = money.lottoCount();
        this.manualLottoCount = manualLottoCount;
        this.autoLottoCount = lottoCount - manualLottoCount;
    }

    private void validateLottoCount(final int manualLottoCount, final Money money) {
        if (manualLottoCount < 0 || manualLottoCount > money.lottoCount()) {
            throw new IllegalArgumentException(BUYING_LOTTO_COUNT_EXCESS_ERROR_MESSAGE);
        }
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }

    public void plus(LottoCount lottoCount) {
        this.lottoCount += lottoCount.lottoCount;
        this.manualLottoCount += lottoCount.manualLottoCount;
        this.autoLottoCount += lottoCount.autoLottoCount;
    }
}
