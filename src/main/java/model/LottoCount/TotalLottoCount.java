package model.LottoCount;

public class TotalLottoCount {

    private static final int LOTTO_PRICE = 1000;
    private static final String MONEY_UNIT_ERROR_MESSAGE = "[ERROR] 투입 금액은 천원 단위의 금액으로 입력하세요.";
    private static final String MONEY_LESS_THAN_MANUAL_PRICE_SUM = "[ERROR] 투입 금액이 수동 구매 로또 가격합 보다 적습니다.";

    private final int autoCount;
    private final ManualLottoCount manualCount;

    public TotalLottoCount(final int Money, final int manualCount) {
        checkValidMoney(Money, manualCount);
        this.autoCount = makeAutoCount(Money, manualCount);
        this.manualCount = makeManualCount(manualCount);
    }

    private void checkValidMoney(final int money, final int manualCount) {
        if (isLessThanLottoPrice(money) || isNotThousandUnits(money)) {
            throw new IllegalArgumentException(MONEY_UNIT_ERROR_MESSAGE);
        }
        if (isLessThanSumOfManualCountPrice(money, manualCount)) {
            throw new IllegalArgumentException(MONEY_LESS_THAN_MANUAL_PRICE_SUM);
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

    private int makeAutoCount(final int money, final int manualCount) {
        return (money / LOTTO_PRICE) - manualCount;
    }

    private ManualLottoCount makeManualCount(final int manualCount) {
        return new ManualLottoCount(manualCount);
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getManualCount() {
        return manualCount.getCount();
    }

    public int getTotalCount() {
        return autoCount + manualCount.getCount();
    }
}
