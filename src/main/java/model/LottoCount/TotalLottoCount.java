package model.LottoCount;

public class TotalLottoCount {

    private static final int LOTTO_PRICE = 1000;
    private static final String MONEY_UNIT_ERROR_MESSAGE = "[ERROR] 투입 금액은 천원 단위의 금액으로 입력하세요.";

    private final AutoLottoCount autoCount;
    private final ManualLottoCount manualCount;

    public TotalLottoCount(final int money, final int manualCount) {
        checkValidMoney(money);
        this.manualCount = makeManualCount(manualCount);
        this.autoCount = makeAutoCount(money);
    }

    private void checkValidMoney(final int money) {
        if (isLessThanLottoPrice(money) || isNotThousandUnits(money)) {
            throw new IllegalArgumentException(MONEY_UNIT_ERROR_MESSAGE);
        }
    }

    private boolean isLessThanLottoPrice(final int money) {
        return money < LOTTO_PRICE;
    }

    private boolean isNotThousandUnits(final int money) {
        return money % LOTTO_PRICE != 0;
    }

    private boolean isLessThanSumOfManualCountPrice(final int money, final int manualCount) {
        return manualCount * LOTTO_PRICE > money;
    }

    private AutoLottoCount makeAutoCount(final int money) {
        int autoCount  = (money / LOTTO_PRICE) - manualCount.getCount();

        return new AutoLottoCount(autoCount);
    }

    private ManualLottoCount makeManualCount(final int manualCount) {
        return new ManualLottoCount(manualCount);
    }

    public int getAutoCount() {
        return autoCount.getCount();
    }

    public int getManualCount() {
        return manualCount.getCount();
    }
}
