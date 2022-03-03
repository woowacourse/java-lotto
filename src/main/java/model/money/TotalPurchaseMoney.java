package model.money;

public class TotalPurchaseMoney {
    private static final int LOTTO_PRICE = 1000;
    private static final String INPUT_MONEY_UNIT_ERROR_MESSAGE = "[ERROR] 투입 금액은 천원 단위의 금액으로 입력하세요.";
    private static final String INPUT_MONEY_LESS_THAN_MANUAL_LOTTO_PRICE_SUM = "[ERROR] 투입 금액이 수동 구매 로또 가격합 보다 적습니다.";

    private final int totalPurchaseMoney;
    private final ManualLottoCount manualLottoCount;

    public TotalPurchaseMoney(final int purchaseMoney, final int manualLottoCount) {
        checkValidMoney(purchaseMoney, manualLottoCount);
        this.totalPurchaseMoney = purchaseMoney;
        this.manualLottoCount = new ManualLottoCount(manualLottoCount);
    }

    private void checkValidMoney(final int purchaseMoney, final int manualLottoCount) {
        if (isLessThanLottoPrice(purchaseMoney) || isNotThousandUnits(purchaseMoney)) {
            throw new IllegalArgumentException(INPUT_MONEY_UNIT_ERROR_MESSAGE);
        }
        if (isLessThanSumOfManualLottoCountPrice(purchaseMoney, manualLottoCount)) {
            throw new IllegalArgumentException(INPUT_MONEY_LESS_THAN_MANUAL_LOTTO_PRICE_SUM);
        }
    }

    private boolean isLessThanLottoPrice(final int purchaseMoney) {
        return purchaseMoney < LOTTO_PRICE;
    }

    private boolean isNotThousandUnits(final int purchaseMoney) {
        return purchaseMoney % LOTTO_PRICE != 0;
    }

    private boolean isLessThanSumOfManualLottoCountPrice(int purchaseMoney, int manualLottoCount) {
        return manualLottoCount * LOTTO_PRICE > purchaseMoney;
    }

    public int getAutoPurchaseCount() {
        return this.getTotalPurchaseLottoCount() - manualLottoCount.getCount();
    }

    public int getManualLottoCount() {
        return manualLottoCount.getCount();
    }

    public int getTotalPurchaseLottoCount() {
        return totalPurchaseMoney / LOTTO_PRICE;
    }
}
