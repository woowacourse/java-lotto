package lotto.model;

public class Money {

    public static final int PRICE_PER_LOTTO = 1000;

    private static final String PRICE_ERROR_MESSAGE = "[ERROR] 유효한 입력이 아닙니다.";

    private final int money;

    public Money(final int money) throws IllegalArgumentException {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(final int money) throws IllegalArgumentException {
        if (money < 0) {
            throw new IllegalArgumentException(PRICE_ERROR_MESSAGE);
        }
    }

    public int count() {
        return money / PRICE_PER_LOTTO;
    }
}
