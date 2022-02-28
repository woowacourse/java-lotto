package model;

public class Money {
    private static final int LOTTO_PRICE = 1000;
    private static final String INPUT_MONEY_UNIT_ERROR_MESSAGE = "[ERROR] 투입 금액은 천원 단위의 금액으로 입력하세요.";

    private final int money;

    public Money(final int money) {
        this.money = checkValidMoney(money);
    }

    private int checkValidMoney(int money) {
        if (isZeroOrLess(money) || isNotThousandUnits(money)) {
            throw new IllegalArgumentException(INPUT_MONEY_UNIT_ERROR_MESSAGE);
        }
        return money;
    }

    private boolean isZeroOrLess(int money) {
        return money <= 0;
    }

    private boolean isNotThousandUnits(int money) {
        return money % LOTTO_PRICE != 0;
    }

    public int makePurchaseCount() {
        return money / LOTTO_PRICE;
    }

    public int getMoney() {
        return money;
    }
}
