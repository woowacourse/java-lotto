package lotto.domain.lotto;

public class LottoCountToBuy {

    private final Count autoCount;
    private final Count manualCount;

    private LottoCountToBuy(Count autoCount, Count manualCount) {
        this.autoCount = autoCount;
        this.manualCount = manualCount;
    }

    public static LottoCountToBuy of(Money money, Count maunalCount) {
        return new LottoCountToBuy(money.countToBuyLotto().subtract(maunalCount), maunalCount);
    }

    public int getManualCount() {
        return manualCount.value();
    }

    public int getAutoCount() {
        return autoCount.value();
    }
}
