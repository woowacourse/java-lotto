package lotto.model;

public class Money {

    private static final int PRICE_PER_LOTTO = 1000;
    private static final String MONEY_ERROR_MESSAGE = "[ERROR] 유효한 구매 금액이 아닙니다.";

    private int money;

    public Money(final int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(final int money) {
        if (money < 0) {
            throw new IllegalArgumentException(MONEY_ERROR_MESSAGE);
        }
    }

    public int getMoney() {
        return money;
    }

    public int lottoCount() {
        return money / PRICE_PER_LOTTO;
    }

    public void plus(Money money) {
        this.money += money.money;
    }
}
