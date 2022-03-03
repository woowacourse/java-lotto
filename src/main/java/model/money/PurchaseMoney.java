package model.money;

public class PurchaseMoney {
    private static final int LOTTO_PRICE = 1000;
    private static final String INPUT_MONEY_UNIT_ERROR_MESSAGE = "[ERROR] 투입 금액은 천원 단위의 금액으로 입력하세요.";

    private final int purchaseMoney;

    public PurchaseMoney(final int purchaseMoney) {
        this.purchaseMoney = checkValidMoney(purchaseMoney);
    }

    private int checkValidMoney(final int money) {
        if (isLessThanLottoPrice(money) || isNotThousandUnits(money)) {
            throw new IllegalArgumentException(INPUT_MONEY_UNIT_ERROR_MESSAGE);
        }
        return money;
    }

    private boolean isLessThanLottoPrice(final int money) {
        return money < LOTTO_PRICE;
    }

    private boolean isNotThousandUnits(final int money) {
        return money % LOTTO_PRICE != 0;
    }

    public int getPurchaseCount() {
        return purchaseMoney / LOTTO_PRICE;
    }
}
