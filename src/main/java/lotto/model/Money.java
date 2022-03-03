package lotto.model;

public class Money {

    private static final int PRICE_PER_LOTTO = 1000;
    public static final String MONEY_ERROR_MESSAGE = "[ERROR] 유효한 구매 금액이 아닙니다.";
    private static final String BUYING_IMPOSSIBLE_ERROR = "[ERROR] 최소 1000원부터 입력해주세요.";

    private final int money;

    public Money(final int money) {
        validateMoney(money);
        buyingPossibleMoney(money);
        this.money = money;
    }

    private void validateMoney(final int money) {
        if (money < 0) {
            throw new IllegalArgumentException(MONEY_ERROR_MESSAGE);
        }
    }

    private void buyingPossibleMoney(final int money) {
        if (money < 1000) {
            throw new IllegalArgumentException(BUYING_IMPOSSIBLE_ERROR);
        }
    }

    public int getMoney() {
        return money;
    }

    public int lottoCount() {
        return money / PRICE_PER_LOTTO;
    }
}
