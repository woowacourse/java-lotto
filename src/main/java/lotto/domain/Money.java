package lotto.domain;

public class Money {
    private static final int MONEY_MIN_CONDITION = 0;
    private static final int LOTTO_PRICE = 1000;

    private final int money;

    public Money(int money) {
        this.money = money;
        validateMoney();
    }

    private void validateMoney() {
        validateMinMoney();
        validateAmount();
    }

    private void validateMinMoney() {
        if (money < MONEY_MIN_CONDITION) {
            throw new IllegalArgumentException("금액은 0이상으로 입력해 주세요");
        }
    }

    private void validateAmount() {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("금액은 천원 단위로 입력해주세요");
        }
    }

    public int ticketAmount() {
        return money / LOTTO_PRICE;
    }

    public int getMoney() {
        return money;
    }
}
