package lotto.domain;

public class Money {
    private final static String ERROR_MONEY_UNIT = "천원 단위로 입력되어야 합니다.";
    private final static int MONEY_UNIT = 1000;

    private final int money;

    public Money(int money) {
        this.money = money;
        validMoneyUnit();
    }

    public int getRound() {
        return money / MONEY_UNIT;
    }

    private void validMoneyUnit() {
        if (money % MONEY_UNIT != 0) {
            throw new IllegalArgumentException(ERROR_MONEY_UNIT);
        }
    }
}
