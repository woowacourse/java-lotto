package lotto.model;

public class Money {

    private static final int PRICE_PER_LOTTO = 1000;
    public static final String MONEY_ERROR_MESSAGE = "[ERROR] 유효한 입력이 아닙니다.";

    private final int money;

    public Money(final int money) throws IllegalArgumentException {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(final int money) throws IllegalArgumentException {
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
}
